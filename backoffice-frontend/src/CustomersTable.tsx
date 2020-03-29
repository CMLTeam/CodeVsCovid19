import React from "react";
import { Customer } from "./dto";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

interface CustomersTableProps {
  customers: Customer[];
}

export const CustomersTable = (props: CustomersTableProps) => {
  const classes = useStyles();

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="left">Document</TableCell>
            <TableCell align="center">Full name</TableCell>
            <TableCell align="center">Status</TableCell>
            <TableCell align="center">Rate</TableCell>
            <TableCell align="center">Address</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.customers.map((row) => (
            <TableRow key={row.id}>
              <TableCell align="left">
                {row.documentId}
              </TableCell>
              <TableCell align="left" title={row.name}>
                {row.name}
              </TableCell>
              <TableCell align="center">{row.status}</TableCell>
              <TableCell align="center">{row.illnessRate}</TableCell>
              <TableCell title={row.address} align="left">{row.address}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};
