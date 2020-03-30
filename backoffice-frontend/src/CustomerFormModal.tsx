import React, { ChangeEvent, useEffect, useState } from "react";
import axios from "axios";
import {
  Button,
  createStyles,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  FormControl,
  Input,
  InputLabel,
  Select,
  Theme,
} from "@material-ui/core";
import { Customer, Status } from "./dto";
import profile from "./assets/profile.jpeg";
import { makeStyles } from "@material-ui/core/styles";
import { sleep } from "./hooks/utils";
import { BACKEND_API } from "./index";

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    dialog: {
      minWidth: "300px",
      margin: "5px",
    },
    content: {
      margin: theme.spacing(3, 0),
      minWidth: 170,
    },
  })
);

interface CustomerFormProps {
  isOpen: boolean;
  update: () => void;
  cancel: () => void;
  customer: Customer | null;
}

export const CustomerFormModal = (props: CustomerFormProps) => {
  const classes = useStyles();
  const [status, setStatus] = useState<Status | undefined>(
    props.customer?.status
  );
  useEffect(() => {
    setStatus(props.customer?.status);
  }, [props.customer]);

  const handleUpdate = async () => {
    console.log("Start to update status");
    await axios.put(`${BACKEND_API}/customers/${props.customer?.id}/status`, {status});
    // await sleep(2);
    console.log("Status updated to", status);
    props.update();
  };

  if (!props.customer) return null;
  const photoStyle = {
    maxHeight: "25vw",
    maxWidth: "20vh",
  };
  const handleChangeStatus = (
    selectValue: ChangeEvent<{ name?: string; value: unknown }>
  ) => {
    setStatus(selectValue.target.value as Status);
  };
  return (
    <Dialog
      open={props.isOpen}
      onClose={props.update}
      aria-labelledby="form-dialog-title"
      disableBackdropClick
      disableEscapeKeyDown
    >
      <DialogTitle id="form-dialog-title">{props.customer.name}</DialogTitle>
      <DialogContent className={classes.dialog}>
        {props.customer.pictureUrl ? (
          <img
            style={photoStyle}
            src={props.customer.pictureUrl}
            alt="Profile photo"
          />
        ) : (
          <img
            style={photoStyle}
            src={profile}
            alt="Profile photo placeholder"
          />
        )}
        <div className={classes.content}>
          <div style={{ textDecoration: "underline" }}>Address</div>
          <div>{props.customer?.address}</div>
        </div>
        <div className={classes.content}>
          <div style={{ textDecoration: "underline" }}>Phone</div>
          <div>{props.customer?.phoneNumber}</div>
        </div>
        <div>
          <FormControl className={classes.content}>
            <InputLabel>Covid-19 Status</InputLabel>
            <Select
              native
              value={status}
              onChange={handleChangeStatus}
              input={<Input />}
            >
              <option value={"recovered"}>RECOVERED</option>
              <option value={"suspected"}>SUSPECTED</option>
              <option value={"positive"}>POSITIVE</option>
              <option value={"negative"}>NEGATIVE</option>
              <option value={"dead"}>DEAD</option>
            </Select>
          </FormControl>
        </div>
      </DialogContent>
      <DialogActions>
        <Button onClick={props.cancel} color="primary">
          Cancel
        </Button>
        <Button onClick={handleUpdate} color="primary">
          Update
        </Button>
      </DialogActions>
    </Dialog>
  );
};
