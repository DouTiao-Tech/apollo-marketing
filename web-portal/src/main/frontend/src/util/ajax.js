import axios from 'axios';
import _ from "lodash";
import {push} from "util/router.js";

/**
 * Axios相关配置
 * @see https://www.npmjs.com/package/axios
 */

/* Creating an instance */
const instance = axios.create({
    timeout: 5000,
    responseType: 'json',
    withCredentials: true, // 是否允许带cookie这些
});

instance.interceptors.response.use(response => response.data, (err) => {
    const response = JSON.parse(JSON.stringify(err)).response;
    if (response.status === 401) {
        push("/login");
    }
    return Promise.reject(err)
});

class Ajax {
    constructor(url) {
        this.url = url;
    }

    path(child) {
        let baseUrl = this.url;
        if (!baseUrl) {
            this.url = child;
            return this;
        }
        if (_.last(baseUrl) === '/') {
            baseUrl = baseUrl.slice(0, -1);
        }
        this.url = _.head(child) === '/' ? baseUrl + child : baseUrl + '/' + child;
        return this;
    }

    query(queryObj) {
        this.params = queryObj;
        return this;
    }

    payload(payload) {
        this.data = payload;
        return this;
    }

    addHeader(key, value) {
        this.headers = _.assign(this.headers, {[key]: value});
        return this;
    }

    get() {
        this.method = "get";
        return instance.request(this);
    }

    post() {
        this.method = "post";
        return instance.request(this);
    }

    delete() {
        this.method = "delete";
        return instance.request(this);
    }

    put() {
        this.method = "put";
        return instance.request(this);
    }
}

export default function ajax(url) {
    return new Ajax(url);
}