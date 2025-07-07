import React, { useState, useEffect, useRef } from "react";
import {
  MdContentCopy,
  MdVolumeUp,
  MdCheck,
  MdSwapHoriz,
  MdOutlineDelete,
  MdKeyboardVoice,
  MdFavoriteBorder,
  MdFavorite,
  MdShare,
} from "react-icons/md";
import CountUp from "../../Reactbits/CountUp/CountUp";
import SplitText from "../../Reactbits/SplitText/SplitText";
import Aurora from "../../Reactbits/Aurora/Aurora";
import BG from "../assets/BG.png";
import LANG from "../assets/Lang.svg";

const Transa = () => {
  const [inputText, setInputText] = useState("");
  const [translatedText, setTranslatedText] = useState("");
  const [inputlanguage, setinputlanguage] = useState("");
  const [language, setLanguage] = useState("en");
  const [detectedLang, setDetectedLang] = useState("");
  const [liked, setLiked] = useState(false);
  const [copied, setCopied] = useState(false);
  const [inputcopied, setInputCopied] = useState(false);
  const textRef = useRef(null);

  const handleTranslate = async () => {
    try {
      const payload = {
        text: inputText,
        targetLanguage: language,
      };

      const response = await fetch("http://localhost:8080/api/translate", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      const data = await response.json();

      setinputlanguage(data.detectedTargetLanguage || "");
      setTranslatedText(data.translatedText || "Translation failed");
      if (data.detectedLang) {
        setDetectedLang(data.detectedLang);
      }
    } catch (error) {
      console.error("Translation error:", error);
      setTranslatedText(`Error: ${error.message}`);
    }
  };

  const handleCopy = () => {
    if (translatedText) {
      navigator.clipboard.writeText(translatedText).then(() => {
        setCopied(true);
        setTimeout(() => setCopied(false), 3000);
      });
    }
  };

  const handleCopyInput = () => {
    if (textRef.current) {
      textRef.current.select();
      navigator.clipboard.writeText(textRef.current.value).then(() => {
        setInputCopied(true);
        setTimeout(() => setInputCopied(false), 3000);
      });
    }
  };

  const handleMicInput = () => {
    if (!("webkitSpeechRecognition" in window)) {
      alert("Speech recognition not supported in this browser.");
      return;
    }

    const recognition = new window.webkitSpeechRecognition();
    recognition.continuous = false;
    recognition.interimResults = false;
    recognition.lang = "en-US";

    recognition.onresult = (event) => {
      const transcript = event.results[0][0].transcript;
      setInputText((prev) => prev + " " + transcript);
    };

    recognition.onerror = (event) => {
      console.error("Speech recognition error:", event.error);
    };

    recognition.start();
  };

  const handleClear = () => {
    setInputText("");
    setTranslatedText("");
    setDetectedLang("");
  };

  const handleSpeak = () => {
    if (!inputText.trim()) return;

    const utterance = new SpeechSynthesisUtterance(inputText);
    const voices = speechSynthesis.getVoices();

    const femaleVoice = voices.find(
      (voice) =>
        voice.name.toLowerCase().includes("female") ||
        voice.name.toLowerCase().includes("woman") ||
        voice.name.toLowerCase().includes("google uk english female")
    );

    utterance.voice = femaleVoice || voices[0];
    utterance.lang = "en-US";
    utterance.rate = 1;
    utterance.pitch = 1.2;

    speechSynthesis.speak(utterance);
  };

  const handleSpeakOut = () => {
    if (!translatedText.trim()) return;

    const utterance = new SpeechSynthesisUtterance(translatedText);
    const voices = speechSynthesis.getVoices();

    const femaleVoice = voices.find(
      (voice) =>
        voice.name.toLowerCase().includes("female") ||
        voice.name.toLowerCase().includes("woman") ||
        voice.name.toLowerCase().includes("google uk english female")
    );

    utterance.voice = femaleVoice || voices[0];
    utterance.lang = "en-US";
    utterance.rate = 1;
    utterance.pitch = 1.2;

    speechSynthesis.speak(utterance);
  };

  const characterCount = inputText.length;

  useEffect(() => {
    window.speechSynthesis.onvoiceschanged = () => {
      speechSynthesis.getVoices();
    };
  }, []);

  return (
    <>
      <div className="flex flex-col items-center justify-center">
        <Aurora />
        <div className="w-full flex flex-col max-w-3xl text-center mb-6 -mt-20">
          <SplitText
            text="AlphaTranslate"
            className="text-3xl font-bold text-gray-300"
            delay={50}
            animationFrom={{ opacity: 0, transform: "translateY(30px)" }}
            animationTo={{ opacity: 1, transform: "translateY(0)" }}
            easing="easeOutQuart"
          />
          <p className="text-gray-400">
            Easily translate text from any language to your desired language in
            seconds. Perfect for communication, learning, and global reach.
          </p>
        </div>
        <div
          className="w-full m-2 max-w-5xl mt-10 sm:mt-0 mx-auto text-white rounded-lg shadow-lg pb-4 relative"
          style={{
  backgroundImage: `url(${BG})`,
  backgroundPosition: "center",
}}

        >
          <div className="absolute inset-0 bg-gray-900/40 backdrop-blur-xl border border-white/20 rounded-lg z-0"></div>

          <div className="relative z-10 space-y-4 p-4">
            <div className="absolute right-2 -top-12 opacity-80 hover:scale-105 transition-all ease-in">
              <img src={LANG} alt="" className="h-18 sm:h-full" />
            </div>
            <div className="flex gap-4 border-b border-gray-700 pb-2">
              <button className="text-gray-300 font-semibold">Text</button>
            </div>

            <div className="flex justify-between rounded-md px-3 py-2 bg-white/10 backdrop-blur-md border border-white/20 items-center gap-4">
              <div className="flex-1">
                <span className="w-full font-extralight bg-transparent text-white">
                  Detect Language {inputlanguage || ""}
                </span>
              </div>

              <button
                className="text-gray-300 hover:text-white"
                title="Swap Languages"
              >
                <MdSwapHoriz size={28} />
              </button>

              <div className="flex-1 relative w-full max-w-md">
                <select
                  value={language}
                  onChange={(e) => setLanguage(e.target.value)}
                  className="w-full text-white font-light focus:outline-none focus:ring-0"
                >
                  {/* List of language options */}
<option value="Arabic">Arabic</option>
<option value="Azerbaijani">Azerbaijani</option>
<option value="Bulgarian">Bulgarian</option>
<option value="Bengali">Bengali</option>
<option value="Bosnian">Bosnian</option>
<option value="Catalan">Catalan</option>
<option value="Czech">Czech</option>
<option value="Welsh">Welsh</option>
<option value="Danish">Danish</option>
<option value="German">German</option>
<option value="Greek">Greek</option>
<option value="English">English</option>
<option value="Spanish">Spanish</option>
<option value="Estonian">Estonian</option>
<option value="Persian">Persian</option>
<option value="Finnish">Finnish</option>
<option value="French">French</option>
<option value="Irish">Irish</option>
<option value="Gujarati">Gujarati</option>
<option value="Hausa">Hausa</option>
<option value="Hindi">Hindi</option>
<option value="Croatian">Croatian</option>
<option value="Hungarian">Hungarian</option>
<option value="Armenian">Armenian</option>
<option value="Indonesian">Indonesian</option>
<option value="Icelandic">Icelandic</option>
<option value="Italian">Italian</option>
<option value="Hebrew">Hebrew</option>
<option value="Japanese">Japanese</option>
<option value="Georgian">Georgian</option>
<option value="Kazakh">Kazakh</option>
<option value="Khmer">Khmer</option>
<option value="Kannada">Kannada</option>
<option value="Korean">Korean</option>
<option value="Lao">Lao</option>
<option value="Lithuanian">Lithuanian</option>
<option value="Latvian">Latvian</option>
<option value="Macedonian">Macedonian</option>
<option value="Malayalam">Malayalam</option>
<option value="Mongolian">Mongolian</option>
<option value="Marathi">Marathi</option>
<option value="Malay">Malay</option>
<option value="Maltese">Maltese</option>
<option value="Nepali">Nepali</option>
<option value="Dutch">Dutch</option>
<option value="Norwegian">Norwegian</option>
<option value="Punjabi">Punjabi</option>
<option value="Polish">Polish</option>
<option value="Portuguese">Portuguese</option>
<option value="Romanian">Romanian</option>
<option value="Russian">Russian</option>
<option value="Sinhala">Sinhala</option>
<option value="Slovak">Slovak</option>
<option value="Slovenian">Slovenian</option>
<option value="Somali">Somali</option>
<option value="Albanian">Albanian</option>
<option value="Serbian">Serbian</option>
<option value="Swedish">Swedish</option>
<option value="Swahili">Swahili</option>
<option value="Tamil">Tamil</option>
<option value="Telugu">Telugu</option>
<option value="Thai">Thai</option>
<option value="Turkish">Turkish</option>
<option value="Ukrainian">Ukrainian</option>
<option value="Urdu">Urdu</option>
<option value="Uzbek">Uzbek</option>
<option value="Vietnamese">Vietnamese</option>
<option value="Chinese">Chinese</option>


                </select>
              </div>
            </div>

            <div className="flex gap-4 justify-between">
              {/* Input text box */}
              <div className="relative w-full rounded-l-md bg-white/10 backdrop-blur-md border border-white/20 md:w-1/2 p-2">
                <div className="absolute top-2 right-2 flex gap-3 bg-gradient-to-b from-black/80 to-transparent px-4 py-2 rounded-full">
                  <button
                    onClick={handleSpeak}
                    className="text-blue-400 hover:text-blue-600"
                    title="Speak Input"
                  >
                    <MdVolumeUp size={22} />
                  </button>
                  <button
                    onClick={handleMicInput}
                    className="text-pink-400 hover:text-pink-600"
                    title="Voice Input"
                  >
                    <MdKeyboardVoice size={22} />
                  </button>
                  <button
                    onClick={handleCopyInput}
                    className="text-blue-400 hover:text-blue-600"
                    title="Copy Input Text"
                  >
                    {inputcopied ? <MdCheck size={22} /> : <MdContentCopy size={22} />}
                  </button>
                </div>
                <textarea
                  ref={textRef}
                  rows={8}
                  className="w-full h-full bg-transparent text-white resize-none focus:outline-none"
                  placeholder="Enter text to translate..."
                  value={inputText}
                  onChange={(e) => setInputText(e.target.value)}
                />
                <div className="text-gray-300 text-sm pt-1 text-right">
                  <CountUp targetNumber={characterCount} /> characters
                </div>
              </div>

              {/* Output translated text box */}
              <div className="relative w-full rounded-r-md bg-white/10 backdrop-blur-md border border-white/20 md:w-1/2 p-2">
                <div className="absolute top-2 right-2 flex gap-3 bg-gradient-to-b from-black/80 to-transparent px-4 py-2 rounded-full">
                  <button
                    onClick={handleSpeakOut}
                    className="text-blue-400 hover:text-blue-600"
                    title="Speak Output"
                  >
                    <MdVolumeUp size={22} />
                  </button>
                  <button
                    onClick={handleCopy}
                    className="text-green-400 hover:text-green-600"
                    title="Copy Output Text"
                  >
                    {copied ? <MdCheck size={22} /> : <MdContentCopy size={22} />}
                  </button>
                </div>
                <textarea
                  readOnly
                  rows={8}
                  className="w-full h-full bg-transparent text-white resize-none focus:outline-none"
                  placeholder="Translated text will appear here..."
                  value={translatedText}
                />
              </div>
            </div>

            {/* Buttons */}
            <div className="flex justify-center gap-4 mt-6">
              <button
                onClick={handleClear}
                className="bg-red-600 hover:bg-red-700 px-4 py-2 rounded text-white transition"
              >
                Clear
              </button>
              <button
                onClick={handleTranslate}
                disabled={!inputText.trim()}
                className={`${
                  inputText.trim()
                    ? "bg-blue-600 hover:bg-blue-700"
                    : "bg-blue-400 cursor-not-allowed"
                } px-4 py-2 rounded text-white transition`}
              >
                Translate
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Transa;