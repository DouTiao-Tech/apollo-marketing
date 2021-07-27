import React from 'react';
import {combineReducers, createStore} from "redux";
import * as reducers from './reducers.js'
import {Provider} from "react-redux";


const store = createStore(combineReducers(reducers));

export const dispatch = (action) => store.dispatch(action);

export const ReduxProvider = ({children}) => (
<Provider store={store}>
    {children}
    </Provider>
);