import React, { useState } from "react";
import { Customer } from "./dto";
import { makeStyles, withStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableContainer from "@material-ui/core/TableContainer";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Paper from "@material-ui/core/Paper";
import { Rating } from "@material-ui/lab";
import FavoriteIcon from "@material-ui/icons/Favorite";
import { getStatusColor } from "./dto/Status";
import { CustomerFormModal } from "./CustomerFormModal";
import useCustomers from "./hooks/useCustomers";

const useStyles = makeStyles({
  table: {
    minWidth: 650,
  },
});

const StyledRating = withStyles({
  iconFilled: {
    color: "#ff6d75",
  },
  iconHover: {
    color: "#ff3d47",
  },
})(Rating);

interface CustomersTableProps {}

export const CustomersTable = (props: CustomersTableProps) => {
  const classes = useStyles();
  const [customers, setCustomers] = useCustomers();
  const [openForm, setOpenForm] = useState<boolean>(false);
  const [currentCustomer, setCurrentCustomer] = useState<Customer | null>(null);

  const handleUpdate = () => {
    setCurrentCustomer(null);
    setOpenForm(false);
    setCustomers();
  };

  const handleCancel = () => {
    setCurrentCustomer(null);
    setOpenForm(false);
  };

  const handleOpenCustomerForm = (customer: Customer) => {
    setCurrentCustomer(customer);
    setOpenForm(true);
  };

  return (
    <TableContainer component={Paper}>
      <Table className={classes.table} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell align="left">Document</TableCell>
            <TableCell align="center">Full name</TableCell>
            <TableCell align="center">Covid-19 Status</TableCell>
            <TableCell align="center">Rate</TableCell>
            <TableCell align="center">Address</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {customers.map((customer) => {

            const rate = customer.illnessRate / 200;
            return (
              <TableRow
                key={customer.id}
                hover
                onClick={() => handleOpenCustomerForm(customer)}
              >
                <TableCell align="left">{customer.documentId}</TableCell>
                <TableCell align="left" title={customer.name}>
                  {customer.name}
                </TableCell>
                <TableCell
                  align="center"
                  style={{
                    textTransform: "uppercase",
                    color: getStatusColor(customer.status),
                  }}
                >
                  {customer.status}
                </TableCell>
                <TableCell align="center" title={String(customer.illnessRate)}>
                  <StyledRating
                    max={5}
                    icon={<FavoriteIcon fontSize="inherit" />}
                    value={rate}
                    precision={0.1}
                    readOnly
                  />
                </TableCell>
                <TableCell title={customer.address} align="left">
                  {customer.address}
                </TableCell>
              </TableRow>
            );
          })}
        </TableBody>
      </Table>
      <CustomerFormModal
        update={handleUpdate}
        isOpen={openForm}
        customer={currentCustomer}
        cancel={handleCancel}
      />
    </TableContainer>
  );
};
