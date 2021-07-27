import React from "react";
import qs from "qs";
import _ from "lodash";
import {Router} from "react-router-dom";
import {createBrowserHistory} from "history"

const history = createBrowserHistory();

const parseOptions = {ignoreQueryPrefix: true};

const stringifyOptions = {addQueryPrefix: true};

export const parseQuery = (location) => {
    const {search} = location;
    return qs.parse(search, parseOptions);
};

export const stringifyQuery = (query) => qs.stringify(query, stringifyOptions);

const typeCast = (v, type) => {
    if (type === 'boolean') return v === 'true';
    if (type === 'number') return Number(v);
    return v;
};

export const pickQuery = (location, paramConfig) => {
    const queryObj = parseQuery(location);
    return _.mapValues(paramConfig, (type, key) => {
        const value = _.get(queryObj, key);
        return value === undefined ? undefined : typeCast(value, type);
    });
};

export const push = (path, state) => history.push(path, state);

export const replace = (path, state) => history.replace(path, state);

export const search = (query) => {
    const pathname = window.location.pathname;
    const search = stringifyQuery(query);
    replace({pathname, search});
};

export const searchAssign = (query) => {
    const oldQuery = parseQuery(window.location);
    search(_.assign({}, oldQuery, query));
};

export const BrowserRouter = ({children}) => (
    <Router history={history}>
        {children}
    </Router>
);