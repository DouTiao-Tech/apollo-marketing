import React, {Component} from "react";
import {Form, Input} from "antd";
import commonCss from "common/common.less";
import {findAll} from "api/category.js";
import {saveArticle} from "api/article.js";
import {push} from "util/router.js";
import _ from "lodash";

const FormItem = Form.Item;

export class ArticleModal extends Component {
    state = {};

    constructor(props) {
        super(props);
        this.props.modalInterface.bindOnOk(this.handleOnOk);
    }

    componentDidMount() {
        this.fetchData();
    }

    async fetchData() {
        const categories = await findAll()

    }

    handleOnOk = async () => {
        const payload = _.pick(this.state, ['title', 'categoryId']);
        const article = await saveArticle(payload);
        push("/article/" + article.id);
    };

    handleTitleChange = (e) => {
        this.setState({title: e.target.value});
    };

    render() {
        return (
            <div className={commonCss.centerSelf}>
                <FormItem label="标题" labelCol={{span: 2}} wrapperCol={{span: 22}}>
                    <Input value={this.state.title} onChange={this.handleTitleChange}/>
                </FormItem>
                <FormItem label="分类" labelCol={{span: 2}} wrapperCol={{span: 22}}>
                    <Input/>
                </FormItem>
            </div>
        );
    }
}