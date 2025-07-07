import React from "react";
import { FaMagic, FaLanguage, FaGlobe, FaTag } from "react-icons/fa";

const features = [
  {
    icon: <FaMagic className="text-green-600 text-2xl" />,
    title: "Instant",
    description: "Get accurate translations in just a few seconds",
    border: "border-l-4 border-green-600",
  },
  {
    icon: <FaLanguage className="text-yellow-500 text-2xl" />,
    title: "Versatile",
    description: "Translate words, sentences, paragraphs, and more",
    border: "border-l-4 border-yellow-500",
  },
  {
    icon: <FaGlobe className="text-red-500 text-2xl" />,
    title: "Multilingual",
    description: "Try it in 52 different languages and dialects",
    border: "border-l-4 border-red-500",
  },
  {
    icon: <FaTag className="text-purple-500 text-2xl" />,
    title: "Affordable",
    description: "Translate for free—or get more features with Premium",
    border: "border-l-4 border-purple-500",
  },
];

const TranslatorFeatures = () => {
  return (
  <section className=" py-12 px-6 text-center">
  <h2 className="text-3xl md:text-4xl font-bold mb-4">
    AlphaTranslate Translate
  </h2>
  <p className="text-lg mb-10">
    Communicate flawlessly in 52 languages with our easy-to-use language translator.
  </p>

  <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-3 justify-center items-center">
    {features.map((feature, index) => (
      <div
        key={index}
        className={`rounded-lg p-6 text-left shadow-sm ${feature.border} mx-auto`}
      >
        <div className="bg-gray-700 p-3 rounded-md inline-block mb-4">
          {feature.icon}
        </div>
        <h3 className="text-lg font-semibold text-gray-300 mb-2">
          {feature.title}
        </h3>
        <p className="text-gray-400">{feature.description}</p>
      </div>
    ))}
  </div>
</section>

  );
};

export default TranslatorFeatures;
