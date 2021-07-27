import React from "react";
import {Pagination} from "antd";

export const pageableToPagination = (pageable) => {
    if (!pageable) {
        return {};
    }
    const {pageNumber, pageSize} = pageable;
    return {current: pageNumber === undefined ? undefined : pageNumber + 1, pageSize};
};

export class Pager extends React.Component {

    render() {
        let {pageable, align, visible, ...rest} = this.props;
        return visible ? (
            <div style={{textAlign: align, margin: "10px 0"}}>
                <Pagination {...pageableToPagination(pageable)} {...rest}/>
            </div>
        ) : null;
    }
}