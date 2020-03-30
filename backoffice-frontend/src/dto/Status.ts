export type Status =
  | "positive"
  | "negative"
  | "suspected"
  | "recovered"
  | "dead";

export const getStatusColor = (status: Status): string => {
  switch (status) {
    case "suspected":
      return "darkorange";
    case "negative":
    case "recovered":
      return "green";
    case "positive":
      return "red";
    case "dead":
      return "darkred";
    default:
      return "";
  }
};
