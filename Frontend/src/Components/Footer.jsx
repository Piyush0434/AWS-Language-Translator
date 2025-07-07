import React from 'react'

const Footer = () => {
  return (
    <div>
        <footer className="bg-black text-gray-400 text-sm py-4 px-6 mt-10 border-t border-gray-800">
  <div className="flex flex-col md:flex-row justify-between items-center max-w-7xl mx-auto">
    <p className="mb-2 md:mb-0">&copy; {new Date().getFullYear()} AlphaTranslate. All rights reserved.</p>
    <div className="flex space-x-4">
      <a href="/about" className="hover:text-white transition duration-200">About</a>
      <a href="/contact" className="hover:text-white transition duration-200">Contact</a>
      <a href="https://github.com" target="_blank" rel="noopener noreferrer" className="hover:text-white transition duration-200">
        GitHub
      </a>
    </div>
  </div>
</footer>

    </div>
  )
}

export default Footer