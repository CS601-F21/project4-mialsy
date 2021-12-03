import React from "react";
import { Button } from "antd";
import { getAxiosOptions } from "../utils/AxiosUtil";
import axios from "axios";

const EventBuyTicketButton = (props) => {
    const outOfStock = props.count === 0;

    const handleOnClick = (id) => {
        const opt = getAxiosOptions("post", `/transactions?eventId=${id}`);

        axios(opt)
            .then(res => {
                if (res.status === 200) {
                    props.setReload(true);
                }
            })
            .catch(err => {
                console.log(err);
            });
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