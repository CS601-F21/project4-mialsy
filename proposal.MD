# Project 4 - Ticket Purchase Web Application Proposal

## Implementation Plan

- Front-End:

  [React](!https://reactjs.org/), using [antd](!https://ant.design/) or [material UI](!https://material-ui.com/) components;

  use [axios](!https://github.com/axios/axios) to call backend-API

- Back-End:

  [Spring](!https://spring.io/projects/spring-framework) framework;

  [Hibrenate](!https://hibernate.org/) for data presistence;

- Deployment:

  AWS

## Proposed Addition Features

| Pts | Featuer   | Plan                    |
| --- | --------- | ----------------------- |
| 10  | Templates | Use React for front-end |
| 10  | Hosted    | Host on AWS             |

## API design

| APIs              | Method | Path          | Need Authentication? | Request Body(if post)                  | Response                                                              |
| ----------------- | ------ | ------------- | -------------------- | -------------------------------------- | --------------------------------------------------------------------- |
| Login             | POST   | /login        | No                   | username, password                     | OK or error info                                                      |
| Logout            | POST   | /logout       | Yes                  | NA                                     | OK or error info                                                      |
| View transactions | GET    | /transactions | Yes                  | NA                                     | list of transactions (date, event, ticket id/price/count, total paid) |
| View events       | GET    | /events       | No                   | NA                                     | list of events (name, id)                                             |
| View event        | GET    | /event/id=xxx | No                   | NA                                     | event (name, ticket price, ticket count)                              |
| Create event      | POST   | /event        | Yes                  | event info, ticket price, ticket count | OK or error info                                                      |
| Purchase ticket   | POST   | /paurchase    | Yes                  | ticket id, ticket count                | OK or error info                                                      |
| Transfer ticket   | POST   | /transfer     | Yes                  | ticket id, ticket count, to username   | OK or error info                                                      |
