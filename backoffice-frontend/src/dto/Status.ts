export type Status = "normal" | "analysis" | "ill";

export const getStatusColor = (status: Status): string => {
  switch (status) {
    case "analysis":
      return "#ff4500";
    case "ill":
      return "#dc143c";
    case "normal":
      return "";
    default:
      return "";
  }
};
