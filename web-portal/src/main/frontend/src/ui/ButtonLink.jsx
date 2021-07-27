import React from "react";
import {Button} from "antd";
import {push} from "util/router.js";

/**
 * Button的href会重刷页面
 */
export const ButtonLink = ({href, onClick, ...rest}) => (
    <Button {...rest} style={{padding: "0 6px"}} type="link"
            onClick={() => onClick ? onClick() : push(href)}/>
);