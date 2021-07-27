import React, {Component} from "react";
import {NavLink, Redirect, Route, Switch, withRouter} from "react-router-dom";
import {DashboardPage} from "layout/DashboardPage.jsx";
import {ArticleList} from "layout/ArticleList.jsx";
import {Dropdown, Icon, Layout, Menu} from "antd";
import commonCss from "common/common.less";
import css from "./App.less";
import _ from "lodash";
import LoginPage from "layout/LoginPage.jsx";
import {ButtonLink, Copyright, FlexBox, FlexContent} from "ui";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import * as actions from "module/actions.js";
import {currentUser, logout} from "api/session.js";
import classNames from "classnames";
import {BrowserRouter as Router, push} from "util/router.js";
import {ArticleEdit} from "layout/ArticleEdit.jsx";

const {Header, Sider, Content, Footer} = Layout;

const Sidebar = ({location, collapsed}) => {
    const path = _.chain(location.pathname).split("/").get(1).value();
    return (
        <Sider trigger={null} collapsible collapsed={collapsed}>
            <Menu selectedKeys={[path || "dashboard"]}
                  theme="dark" mode="inline">
                <Menu.Item key="dashboard">
                    <Icon type="dashboard"/>
                    <span>dashboard</span>
                    <NavLink to="/dashboard"/>
                </Menu.Item>
                <Menu.Item key="article">
                    <Icon type="form"/>
                    <span>article</span>
                    <NavLink to="/article"/>
                </Menu.Item>
                <Menu.Item key="tmp">
                    <Icon type="upload"/>
                    <span>nav 3</span>
                    <NavLink to="/tmp"/>
                </Menu.Item>
            </Menu>
        </Sider>
    );
};

const getUserMenu = (onLogout) => () => (
    <Menu>
        <Menu.Item>
            <ButtonLink href="/article">
                <Icon type="setting"/> User Center
            </ButtonLink>
        </Menu.Item>
        <Menu.Item>
            <ButtonLink onClick={onLogout}>
                <Icon type="logout"/> Log Out
            </ButtonLink>
        </Menu.Item>
    </Menu>
);

const HeadLine = ({user, collapsed, onToggle, onLogout}) => {
    return (
        <FlexBox>
            <span className={classNames(css.btn, css.btn)} onClick={onToggle}>
                <Icon type={collapsed ? 'menu-unfold' : 'menu-fold'}/>
            </span>
            <FlexContent/>
            <span className={css.text}>上次登录时间：{user.lastLoginTime}</span>
            <Dropdown overlay={getUserMenu(onLogout)} placement="bottomRight">
                <span className={classNames(css.btn, css.btn)}>
                    <Icon type="user"/>
                    <span className={css.username}>{user.username}</span>
                </span>
            </Dropdown>
        </FlexBox>
    );
};

@withRouter
@connect(state => _.pick(state, 'user'), dispatch => bindActionCreators(actions, dispatch))
class Root extends Component {

    state = {collapsed: false};

    componentDidMount() {
        const {userLogin} = this.props;
        console.log(userLogin);
        // currentUser().then(user => userLogin(user), () => push("/login"))
    }

    toggle = () => this.setState(({collapsed}) => ({collapsed: !collapsed}));

    logout = async () => {
        const {userLogout} = this.props;
        await logout();
        userLogout();
        push("/login");
    };

    render() {
        const {user, location} = this.props;
        return (
            <Layout style={{minWidth: 740}} className={commonCss.fullHeight}>
                <Sidebar location={location}
                         collapsed={this.state.collapsed}/>
                <Layout>
                    <Header className={css.header}>
                        <HeadLine collapsed={this.state.collapsed}
                                  user={user || {}}
                                  onLogout={this.logout}
                                  onToggle={this.toggle}/>
                    </Header>
                    <Content className={css.content}>
                        <Switch>
                            <Route path="/dashboard" component={DashboardPage}/>
                            <Route path="/article" exact component={() => <ArticleList/>}/>
                            <Route path="/article/:id" component={ArticleEdit}/>
                            <Redirect to="/dashboard"/>
                        </Switch>
                    </Content>
                    <Footer><Copyright/></Footer>
                </Layout>
            </Layout>
        );
    }
}

export default class App extends Component {
    render() {
        console.log(process.env);
        return (
            <Router>
                <Switch>
                    <Route path="/login" component={LoginPage}/>
                    <Route path="/" component={Root}/>
                </Switch>
            </Router>
        );
    }
};