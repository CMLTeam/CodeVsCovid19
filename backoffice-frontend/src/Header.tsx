import React from 'react';
import logo from "./COVID-19_World_Map.svg";

interface HeaderProps {

}

export const Header = (props: HeaderProps) => {
  return (
    <div>
      <img style={{maxHeight: "10vh", width: "80vw"}} src={logo} alt="logo" />

    </div>
  );
};