import React from "react";
import { Button, message } from "antd";
import { getAxiosOptions } from "../../utils/AxiosUtil";
import axios from "axios";

/**
 * Buy Ticket Button for the event pages
 *
 * @param {*} props props from parent component
 * @returns a button component for buying ticket
 */
const EventBuyTicketButton = (props) => {
  const outOfStock = props.count === 0;

  const handleOnClick = (id) => {
    const opt = getAxiosOptions("post", `/transactions?eventId=${id}`);

    axios(opt)
      .then((res) => {
        if (res.status === 200) {
          props.setReload(true);
        }
      })
      .catch((err) => {
        console.log(err);
        message.error("Cannot perform buying ticket at this time.");
      });
  };

  return (
    <Button
      type={"primary"}
      disabled={outOfStock || props.isPassed}
      onClick={() => {
        handleOnClick(props.id);
      }}
    >
      {props.isPassed ? "Past Event" : outOfStock ? "Sold out" : "Buy Ticket"}
    </Button>
  );
};
export default EventBuyTicketButton;
