import React, {Component, Fragment} from 'react';
import {deleteArticle, likeFuzzyTitle, likeTitlePrefix, search} from "service/article.js";
import {AutoComplete, Card, Icon, Input, List, message, Modal, Select, Tooltip} from "antd";
import css from "./ArticleList.less";
import commonCss from "common/common.less";
import _ from "lodash";
import {ArticleModal} from "component/ArticleModal.jsx";
import {ModalWrapper} from "ui/Modal.jsx";
import {NavLink, withRouter} from "react-router-dom";
import {pickQuery, searchAssign} from "util/router.js";
import {pageableToPagination} from "ui/Pager.jsx";
import {ButtonLink} from "ui";

@withRouter
export class ArticleList extends Component {

    tabList = [{key: true, tab: "已发表"}, {key: false, tab: "草稿箱"}];

    paramConfig = {published: 'boolean', page: 'number', size: 'number'};

    state = {};

    componentDidMount() {
        const {location} = this.props;
        const {published = true, page = 1, size = 10} = pickQuery(location, this.paramConfig);
        this.fetchData(published, page, size);
    }

    async fetchData(published, page, size) {
        this.setState({published, page, size, loading: true});
        const data = await search({published, deleted: false}, page, size);
        this.setState({data, loading: false});
    }

    refresh() {
        const {published, page, size} = this.state;
        this.fetchData(published, page, size);
    }

    handleTabChange = (published) => searchAssign({published, page: 1});

    handlePagerChange = (page, size) => searchAssign({page, size});

    handleCompleteSearch = async (value) => {
        const searchSuggest = await likeTitlePrefix(value);
        this.setState({searchSuggest});
    };

    handleSearch = async (value) => {
        const data = await likeFuzzyTitle(value);
        console.log(data);
    };

    handleDelete = (articleId) => {
        Modal.confirm({
            title: "确认删除改文章",
            content: "文章会在回收站保留三天",
            onOk: async () => {
                try {
                    await deleteArticle(articleId);
                    message.success("删除成功");
                    this.refresh();
                } catch (e) {
                    console.log(e)
                }
            }
        });
    };
    handleDeny = (articleId) => {
        console.log(articleId);
    };
    handleNew = () => {
        this.setState({showModal: true});
    };

    handleModalClose = () => {
        this.setState({showModal: false});
    };

    renderCardExtra = () => {
        const {searchSuggest = []} = this.state;
        const options = _.map(searchSuggest, ({id, title}) => (
            <Select.Option key={id} value={_.toString(id)}>{title}</Select.Option>)
        );
        return (
            <Fragment>
                <Tooltip title="新建">
                    <Icon className={css.edit} type="edit" onClick={this.handleNew}/>
                </Tooltip>
                <AutoComplete dataSource={options} onSearch={this.handleCompleteSearch}>
                    <Input.Search placeholder="输入文章标题" onSearch={this.handleSearch}/>
                </AutoComplete>
            </Fragment>
        );
    };

    renderItem = (item) => {
        let {id, title, created, modified, views, comments} = item;
        return (
            <div key={id} className={css.article}>
                <h2><NavLink to={`/article/${id}`}>{title}</NavLink></h2>
                <div className={commonCss.flexBox}>
                    <div>
                    <span className={commonCss.span}>
                        <Icon type="eye"/>
                        <span className={css.num}>{views}</span>
                    </span>
                        <span className={commonCss.span}>
                        <Icon type="message"/>
                        <span className={css.num}>{comments}</span>
                    </span>
                        <span className={commonCss.span}>创建时间：{created}</span>
                        <span className={commonCss.span}>最后修改时间：{modified}</span>
                    </div>
                    <div className={commonCss.flex1}/>
                    <div>
                        <ButtonLink href={`/article/${id}`}>查看</ButtonLink>
                        <ButtonLink onClick={() => this.handleDeny(id)}>禁止评论</ButtonLink>
                        <ButtonLink onClick={() => this.handleDelete(id)}>删除</ButtonLink>
                    </div>
                </div>
            </div>
        );
    };

    render() {
        const {loading, published, data = {}} = this.state;
        const {content, totalElements, pageable} = data;
        return (
            <Card title="文章列表" activeTabKey={_.toString(published)}
                  tabList={this.tabList} onTabChange={this.handleTabChange}
                  extra={this.renderCardExtra()}>
                <List dataSource={content}
                      loading={loading}
                      renderItem={this.renderItem}
                      pagination={{
                          showSizeChanger: true,
                          showQuickJumper: true,
                          total: totalElements,
                          onShowSizeChange: this.handlePagerChange,
                          onChange: this.handlePagerChange,
                          ...pageableToPagination(pageable)
                      }}/>
                <ModalWrapper title="新建" visible={this.state.showModal}
                              onCancel={this.handleModalClose}>
                    <ArticleModal/>
                </ModalWrapper>
            </Card>
        );
    }
}