export type Status = "positive" | "negative" | "suspected" | "recovered" | "dead"
;

export const getStatusColor = (status: Status): string => {
  switch (status) {
    case "suspected":
      return "#ff4500";
    case "negative":
      return "#dc143c";
    default:
      return "";
  }
};
