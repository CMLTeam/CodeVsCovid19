import React from "react";
import logo from "./COVID-19_World_Map.svg";

interface HeaderProps {}

export const Header = (props: HeaderProps) => {
  return (
    <div>
      <div style={{ display: "inline-block" }}>
        <img
          style={{ height: "9vw", maxWidth: "50vh" }}
          src={logo}
          alt="logo"
        />
      </div>

      <div
        style={{
          display: "inline-block",
          fontSize: "11vw",
          width: "70%",
        }}
      >
        C O V o I D e r
      </div>
    </div>
  );
};
