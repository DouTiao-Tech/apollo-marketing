import React from "react";
import commonCss from "common/common.less";
import {Button, Form, Layout} from "antd";
import {Copyright} from "ui";
import {login} from "service/session.js";
import {dispatch} from "module/store.js";
import {userLogin} from "module/actions.js";
import {push} from "util/router.js";

const {Content, Footer} = Layout;
const FormItem = Form.Item;

class LoginForm extends React.Component {

    handleSubmit = (e) => {
        e.preventDefault();
        this.login(values);
    };
    login = async ({username, password}) => {
        const user = await login(username, password);
        dispatch(userLogin(user));
        push("/");
    };

    render() {
        return (
            <Form className={commonCss.centerSelf}
                  style={{
                      width: 390,
                      padding: "80px 0"
                  }}
                  onSubmit={this.handleSubmit}>
                <FormItem>
                    <Button size="large" type="primary" htmlType="submit"
                            block>登录</Button>
                </FormItem>
            </Form>
        );
    }
}

const LoginPage = () => (
    <Layout className={commonCss.fullHeight}
            style={{
                background: "#f0f2f5 url(https://gw.alipayobjects.com/zos/rmsportal/TVYTbAXWheQpRcWDaDMu.svg) no-repeat center 110px",
                backgroundSize: "100%"
            }}>
        <Content>
            <h2 className={commonCss.centerContent} style={{marginTop: 140}}>
                Backend
            </h2>
            <LoginForm/>
        </Content>
        <Footer><Copyright/></Footer>
    </Layout>
);

export default LoginPage;