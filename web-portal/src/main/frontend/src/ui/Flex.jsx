import React from "react";
import commonCss from "common/common.less";
import classNames from "classnames";

export const FlexBox = ({className, column, children, ...rest}) => (
    <div className={classNames(commonCss.flexBox, column ? commonCss.flexDirection : '', className)} {...rest}>
        {children}
    </div>
);

export const FlexContent = ({className, children, ...rest}) => (
    <div className={classNames(commonCss.flex1, className)} {...rest}>{children}</div>
);