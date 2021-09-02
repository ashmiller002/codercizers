import React, { useRef, useEffect } from "react";
import "./NotFound.css";
import { gsap, Power3 } from "gsap";

function NotFound() {
 
  let errorText1 = useRef(null);
  let paragraph = useRef(null);
  let img = useRef(null);
  let errorContainer = useRef

  useEffect(() => {
    gsap.to(errorContainer, { duration: 0, css: { visibility: "visible" } });
    gsap.from(errorText1, {
        delay: 0.1,
        duration: .5,
        opacity: 0,
        x: 30,
        ease: Power3.easeIn,
      });
      gsap.from(paragraph, {
        delay: 0.1,
        duration: .5,
        opacity: 0,
        x: -30,
        ease: Power3.easeIn,
      });
      gsap.from(img, {
        delay: 0.1,
        duration: .5,
        opacity: 0,
        x: -30,
        ease: Power3.easeIn,
      });
}, [])


  return (
    <div className="container" id="errorContainer">
      <div className="errorContainer" ref={(el) => (errorContainer = el)}>
      <h2 className="col s12 m6 l4 xl4" id="errorText1" ref={(el) => (errorText1 = el)}>404 Error: Page Not Found</h2>
<div className="divider"></div>
<div className="col s12 m6 l4 xl4">
<img className="errorImg"
                ref={(el) => (img = el)}
                src="/errorImage.png"
                alt=""
              />
    </div>
    <div className="divider"></div>
    <div className="col s12 m6 l4 xl4">
    <p className="flow-text" ref={(el) => (paragraph = el)}>
                 Sorry, we can't find that page. Try our{" "}
                 <a href="/">homepage</a>.
              </p>
    </div>
    </div>
    </div>
    
  );
}

export default NotFound;
