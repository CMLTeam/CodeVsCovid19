export const sleep = async (seconds: number = 0) => {
  return new Promise((resolve) => setTimeout(resolve, seconds * 1000));
};
