import React from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  TextField,
} from "@material-ui/core";
import { Customer } from "./dto";

interface CustomerFormProps {
  isOpen: boolean;
  closeForm: () => void;
  customer: Customer | null;
}

export const CustomerFormModal = (props: CustomerFormProps) => {
  if (!props.customer) return null;
  return (
    <Dialog
      open={props.isOpen}
      onClose={props.closeForm}
      aria-labelledby="form-dialog-title"
    >
      <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
      <DialogContent>
        <DialogContentText>
          To subscribe to this website, please enter your email address here. We
          will send updates occasionally.
        </DialogContentText>
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Email Address"
          type="email"
          fullWidth
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={props.closeForm} color="primary">
          Cancel
        </Button>
        <Button onClick={props.closeForm} color="primary">
          Subscribe
        </Button>
      </DialogActions>
    </Dialog>
  );
};
