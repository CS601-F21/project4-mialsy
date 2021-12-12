import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import axios from "axios";
import { Descriptions, Image, Spin } from "antd";
import EventBuyTicketButton from "./EventBuyTicketButton";
import { getAxiosOptions } from "../../utils/AxiosUtil";
import { getFormattedTime, isPassedTime } from "../../utils/DisplayUtil";

/**
 * Get description for the event
 *
 * @param {*} e the event
 * @param {*} setReload set component loading or not
 * @returns detail desription of the event
 */
const getEventDescription = (e, setReload) => {
  return (
    <div>
      <Descriptions title={e.name} column={1}>
        <Descriptions.Item label={<div>Time</div>}>
          {getFormattedTime(e.time)}
        </Descriptions.Item>
        {e.description && (
          <Descriptions.Item label="Description">
            {e.description}
          </Descriptions.Item>
        )}
        {e.count !== 0 ? (
          <Descriptions.Item label="Count">{e.count}</Descriptions.Item>
        ) : (
          <Descriptions.Item>Sold Out</Descriptions.Item>
        )}
      </Descriptions>
      {e.picture && <Image src={e.picture} />}
      <EventBuyTicketButton
        count={e.count}
        id={e.id}
        isPassed={isPassedTime(e.time)}
        setReload={setReload}
      />
    </div>
  );
};

/**
 * Event detail
 *
 * @returns event detail component
 */
const EventDetail = () => {
  const params = useParams();
  const [event, setEvent] = useState(Object);
  const [reload, setReload] = useState(false);

  const fetchData = () => {
    const opt = getAxiosOptions("get", `/event?id=${params.id}`);

    axios(opt)
      .then((res) => {
        if (res.status === 200) {
          const eventData = res.data;
          setEvent(eventData);
          setReload(false);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    fetchData();
  }, [reload]);

  useEffect(() => {}, [event.id]);

  return !event.id ? (
    <div>
      <Spin size="large" style={{ width: "80%", margin: "0 auto" }} />
    </div>
  ) : (
    getEventDescription(event, setReload)
  );
};
export default EventDetail;
