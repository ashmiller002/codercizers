import React, {useRef, useEffect, useState} from 'react';
import './NotFound.css';
import {gsap, Power3} from "gsap";




function NotFound() {

    let notFound = useRef(null);
    let square = useRef(null);
    let squareTwo = useRef(null);
    let squareThree = useRef(null);
    let pnf = useRef(null);
    let paragraph = useRef(null);
    let img = useRef(null);

    const [squareSize, setSquareSize] = useState(false);
    const [squareTwoSize, setSquareTwoSize] = useState(false);
    const [squareThreeSize, setSquareThreeSize] = useState(false);
    

    const handleSquareExpand = () => {
        gsap.to(square, {duration: .8, width: 200, height: 200, x: 30, ease: Power3.easeOut} )
        setSquareSize(true);
      }
    
      const handleSquareShrink = () => {
        gsap.to(square, {duration: .8, width: 75, height: 75, x: 0, ease: Power3.easeOut} )
        setSquareSize(false);
      }
    
      const handleSquareTwoExpand = () => {
        gsap.to(squareTwo, {duration: .8, width: 200, height: 200, x: 30, ease: Power3.easeOut} )
        setSquareTwoSize(true);
      }
    
      const handleSquareTwoShrink = () => {
        gsap.to(squareTwo, {duration: .8, width: 75, height: 75, x: 0, ease: Power3.easeOut} )
        setSquareTwoSize(false);
      }
    
      const handleSquareThreeExpand = () => {
        gsap.to(squareThree, {duration: .8, width: 200, height: 200, x: 30, ease: Power3.easeOut} )
        setSquareThreeSize(true);
      }
    
      const handleSquareThreeShrink = () => {
        gsap.to(squareThree, {duration: .8, width: 75, height: 75, x: 0, ease: Power3.easeOut} )
        setSquareThreeSize(false);
      }

    useEffect(()=>{
        gsap.to(notFound, {duration: 0, css:{visibility: "visible"}})
        gsap.from(square, {duration: 1, opacity: 0, x: -30, ease: Power3.easeIn})
        gsap.from(squareTwo, {delay: 0.2, duration: 1, opacity: 0, x: -30, ease: Power3.easeIn})
        gsap.from(squareThree, {delay: 0.4, duration: 1, opacity: 0, x: -30, ease: Power3.easeIn})
        gsap.from(pnf, {delay: 0.4, duration: 1, opacity: 0, x: 30, ease: Power3.easeIn})
        gsap.from(paragraph, {delay: 0.4, duration: 1, opacity: 0, y: -30, ease: Power3.easeIn})
        gsap.from(img, {delay: 0.4, duration: 1, opacity: 0, y:30, x: 30, ease: Power3.easeIn})
        
      },[])

    return (
        <div ref={ el=> notFound = el} className="container">
             <h1  ref={el=>pnf=el} className="pnf">404: Page Not Found</h1> 
             <p ref={ el=> paragraph = el}>Sorry, we couldn't find this page. But don't worry, we can
         find other things from our <a href="/">homepage</a>.</p>
         <img ref={el=>img=el} src="/errorImage.png" alt=""/>
        <div className="squareContainer">
         <div ref={el=>square=el} onClick={squareSize !== true ? handleSquareExpand : handleSquareShrink}className="square"></div>
         <div ref={el=>squareTwo=el} onClick={squareTwoSize !== true ? handleSquareTwoExpand : handleSquareTwoShrink} className="square two"></div>
         <div ref={el=>squareThree=el} onClick={squareThreeSize !== true ? handleSquareThreeExpand : handleSquareThreeShrink}className="square three"></div>
        </div>
        </div>
    )
}

export default NotFound;