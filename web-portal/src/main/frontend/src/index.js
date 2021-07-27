import React from "react";
import ReactDOM from "react-dom";
import App from "./App.jsx";
import {ReduxProvider} from "module/store.js";

ReactDOM.render(<ReduxProvider><App/></ReduxProvider>, document.getElementById('root'));