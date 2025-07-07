import React from "react";
import Navbar from "./Components/Navbar";
import Transa from "./Components/Transa";
import TranslatorFeatures from "./Components/TranslatorFeatures";
import TranslatorGuide from "./Components/TranslatorGuide";
import Footer from "./Components/Footer";

const App = () => {
  return (
    <div>
      <Navbar />
      <div className="bg-black  text-gray-300">
        <Transa />
        <TranslatorFeatures />
        <TranslatorGuide />
        <Footer/>
      </div>
    </div>
  );
};

export default App;
