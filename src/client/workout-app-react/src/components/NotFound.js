import React, { useRef, useEffect, useState } from "react";
import "./NotFound.css";
import { gsap, Power3 } from "gsap";

function NotFound() {
  let notFound = useRef(null);
  let square = useRef(null);
  let squareTwo = useRef(null);
  let squareThree = useRef(null);
  let pnf = useRef(null);
  let paragraph = useRef(null);
  let img = useRef(null);
  let imgBorder = useRef(null);
  let imgBorder2 = useRef(null);
  let imgBorder3 = useRef(null);

  const [squareSize, setSquareSize] = useState(false);
  const [squareTwoSize, setSquareTwoSize] = useState(false);
  const [squareThreeSize, setSquareThreeSize] = useState(false);

  const handleSquareExpand = () => {
    gsap.to(square, {
      duration: 0.8,
      width: 200,
      height: 200,
      x: 30,
      ease: Power3.easeOut,
      opacity: .5
    });
    setSquareSize(true);
  };

  const handleSquareShrink = () => {
    gsap.to(square, {
      duration: 0.8,
      width: 75,
      height: 75,
      x: 0,
      ease: Power3.easeOut,
      opacity: 1
    });
    setSquareSize(false);
  };

  const handleSquareTwoExpand = () => {
    gsap.to(squareTwo, {
      duration: 0.8,
      width: 200,
      height: 200,
      x: 30,
      ease: Power3.easeOut,
      opacity: .5
    });
    setSquareTwoSize(true);
  };

  const handleSquareTwoShrink = () => {
    gsap.to(squareTwo, {
      duration: 0.8,
      width: 75,
      height: 75,
      x: 0,
      ease: Power3.easeOut,
      opacity: 1
    });
    setSquareTwoSize(false);
  };

  const handleSquareThreeExpand = () => {
    gsap.to(squareThree, {
      duration: 0.8,
      width: 200,
      height: 200,
      x: 30,
      ease: Power3.easeOut,
      opacity: .5
    });
    setSquareThreeSize(true);
  };

  const handleSquareThreeShrink = () => {
    gsap.to(squareThree, {
      duration: 0.8,
      width: 75,
      height: 75,
      x: 0,
      ease: Power3.easeOut,
      opacity: 1
    });
    setSquareThreeSize(false);
  };

  useEffect(() => {
    gsap.to(notFound, { duration: 0, css: { visibility: "visible" } });
    gsap.from(square, { duration: 1, opacity: 0, x: -30, ease: Power3.easeIn });
    gsap.from(squareTwo, {
      delay: 0.2,
      duration: 1,
      opacity: 0,
      x: -30,
      ease: Power3.easeIn,
    });
    gsap.from(squareThree, {
      delay: 0.4,
      duration: 1,
      opacity: 0,
      x: -30,
      ease: Power3.easeIn,
    });
    gsap.from(pnf, {
      delay: 0.4,
      duration: 1,
      opacity: 0,
      x: 30,
      ease: Power3.easeIn,
    });
    gsap.from(paragraph, {
      delay: 0.4,
      duration: 1,
      opacity: 0,
      y: -30,
      ease: Power3.easeIn,
    });
    gsap.from(img, {
      delay: 0,
      duration: 1,
      opacity: 0,
      y: 30,
      x: -30,
      ease: Power3.easeIn,
    });
    gsap.from(imgBorder, {
        delay: 0.2,
        duration: 1,
        opacity: 0,
        y: 30,
        x: -30,
        ease: Power3.easeIn,
      });
      gsap.from(imgBorder2, {
        delay: 0.3,
        duration: 1,
        opacity: 0,
        y: 30,
        x: -30,
        ease: Power3.easeIn,
      });
      gsap.from(imgBorder3, {
        delay: 0.4,
        duration: 1,
        opacity: 0,
        y: 30,
        x: -30,
        ease: Power3.easeIn,
      });
  }, []);

  return (
    <div ref={(el) => (notFound = el)} className="divContainer">
      <h1 ref={(el) => (pnf = el)} className="pnf">
        404: Page Not Found
      </h1>
      <p ref={(el) => (paragraph = el)}>
        Sorry, we couldn't find this page. But don't worry, we can find other
        things from our <a href="/">homepage</a>.
      </p>
      <div ref={(el) => (imgBorder = el)}className="imgBorder"></div>
      <div ref={(el) => (imgBorder2 = el)}className="imgBorder2"></div>
      <div ref={(el) => (imgBorder3 = el)}className="imgBorder3"></div>
      <img ref={(el) => (img = el)} src="/errorImage.png" alt="" />
      <div className="squareContainer">
        <div
          ref={(el) => (square = el)}
          onMouseOver={
            squareSize !== true ? handleSquareExpand : handleSquareShrink
          }
          className="square"
        ></div>
        <div
          ref={(el) => (squareTwo = el)}
          onMouseOver={
            squareTwoSize !== true
              ? handleSquareTwoExpand
              : handleSquareTwoShrink
          }
          className="square two"
        ></div>
        <div
          ref={(el) => (squareThree = el)}
          onMouseOver={
            squareThreeSize !== true
              ? handleSquareThreeExpand
              : handleSquareThreeShrink
          }
          className="square three"
        ></div>
      </div>
    </div>
  );
}

export default NotFound;
