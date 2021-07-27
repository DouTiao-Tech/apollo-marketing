import React, {Component} from 'react';
import {findArticleById} from "api/article.js";

export class ArticleEdit extends Component {

    state = {};

    componentDidMount() {
        const params = this.props.match.params;
        this.fetchData(params.id);
    }

    async fetchData(id) {
        const article = await findArticleById(id);
        this.setState({article});
    }

    render() {
        console.log(this.state);

        const input = '# This is a header\n\nAnd this is a paragraph'
        return <b>开发中...{input}</b>;
    }
}