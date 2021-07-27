import React from "react";
import commonCss from "common/common.less";

const Copyright = () => {
    const year = new Date().getFullYear();
    return (
        <div className={commonCss.centerContent}>
            <a href="http://www.miitbeian.gov.cn/" target="_blank">
                赣ICP备17009276号
            </a>
            <span> &copy; </span>
            <span> 2016 - {year} </span>
            <span>power by <a href="//www.hufeifei.cn">胡飞飞</a></span>
        </div>
    );
};

export default Copyright;