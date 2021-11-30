import React from "react";
import { Button } from "antd";

const EventBuyTicketButton = (props) => {
    const outOfStock = props.count === 0;

    const handleOnClick = (id) => {
        console.log(id);
        props.setReload(true);
    };

    return (
        <Button type={'primary'} 
            disabled={outOfStock} 
            onClick={() => {handleOnClick(props.id) }}
        >
            {outOfStock ? 'Sold out' : 'Buy ticket'}
        </Button>
    );
};
export default EventBuyTicketButton;