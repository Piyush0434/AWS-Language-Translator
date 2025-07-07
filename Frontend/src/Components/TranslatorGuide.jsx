import React from "react";
import GradientText from "../../Reactbits/GradientText/GradientText";

const TranslatorGuide = () => {
  return (
    <div className="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8 py-12 text-center">
      {/* CTA Button */}
      <GradientText
        colors={["#40ffaa", "#4079ff", "#40ffaa", "#4079ff", "#40ffaa"]}
        animationSpeed={3}
        showBorder={true}
        className="custom-class px-4 py-2 mb-10"
      >
        Get started now
      </GradientText>

      {/* Heading */}
      <h1 className="text-3xl sm:text-4xl font-bold text-gray-100 mb-4">
        How to use AlphaTranslate Translator tool?
      </h1>
      <p className="text-gray-300 mb-12 max-w-2xl mx-auto">
        AlphaTranslate's free Translator helps you translate any text in just
        a few seconds. To use our tool, just follow these three simple steps.
      </p>

      {/* Steps Grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-10 text-left">
        {/* Step 1 */}
        <div className="p-6 bg-white/5 rounded-lg backdrop-blur-md shadow-md">
          <div className="bg-green-600 w-10 h-10 flex items-center justify-center rounded-full text-white font-bold text-lg mb-4">
            1
          </div>
          <h2 className="text-xl font-semibold text-gray-100 mb-2">Add text</h2>
          <p className="text-gray-300 text-sm">
            Type, paste, or upload the text you want to translate. There are no
            limits on usage.
          </p>
        </div>

        {/* Step 2 */}
        <div className="p-6 bg-white/5 rounded-lg backdrop-blur-md shadow-md">
          <div className="bg-green-600 w-10 h-10 flex items-center justify-center rounded-full text-white font-bold text-lg mb-4">
            2
          </div>
          <h2 className="text-xl font-semibold text-gray-100 mb-2">
            Choose language
          </h2>
          <p className="text-gray-300 text-sm">
            Select from over 50 supported languages including Spanish, German,
            and French.
          </p>
        </div>

        {/* Step 3 */}
        <div className="p-6 bg-white/5 rounded-lg backdrop-blur-md shadow-md">
          <div className="bg-green-600 w-10 h-10 flex items-center justify-center rounded-full text-white font-bold text-lg mb-4">
            3
          </div>
          <h2 className="text-xl font-semibold text-gray-100 mb-2">
            Get translation
          </h2>
          <p className="text-gray-300 text-sm">
            Click “Translate” to generate your result. You can then copy or
            export it as needed.
          </p>
        </div>
      </div>
    </div>
  );
};

export default TranslatorGuide;
