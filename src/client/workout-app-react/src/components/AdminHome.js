import React, { useRef, useEffect, useState } from "react";
import "./AdminHome.css";
import { gsap, Power3 } from "gsap";

function AdminHome() {
  let welcomeText = useRef(null);
  let text = useRef(null);
  let text2 = useRef(null);
  let img = useRef(null);
  let edit = useRef(null);
  let disable = useRef(null);
  let add = useRef(null);
  let adminPage = useRef(null);

  useEffect(() => {
    gsap.to(adminPage, { duration: 0, css: { visibility: "visible" } });
    gsap.from(welcomeText, {
      delay: 0,
      duration: 1,
      opacity: 0,
      y: 100,
      ease: Power3.easeIn,
    });
    gsap.from(text, {
      delay: 0.4,
      duration: 1,
      opacity: 0,
      y: 100,
      ease: Power3.easeIn,
    });
    gsap.from(text2, {
      delay: 0.9,
      duration: 1,
      opacity: 0,
      y: 100,
      ease: Power3.easeIn,
    });
    gsap.from(img, {
      delay: 0.5,
      duration: 1.5,
      opacity: 0,
      scale: 0.3,
      ease: Power3.easeIn,
    });
    gsap.from(edit, {
      delay: 0.5,
      duration: 1.5,
      opacity: 0,
      scale: 0.3,
      ease: Power3.easeIn,
    });
    gsap.from(disable, {
      delay: 0.9,
      duration: 1.5,
      opacity: 0,
      scale: 0.3,
      ease: Power3.easeIn,
    });
    gsap.from(add, {
      delay: 1.3,
      duration: 1.5,
      opacity: 0,
      scale: 0.3,
      ease: Power3.easeIn,
    });
  }, []);

  return (
    <div className="container">
      <div className="row">
        <div className="adminPage" ref={(el) => (adminPage = el)}>
          <div col s12 m6 l4 xl4>
            <div ref={(el) => (welcomeText = el)} className="welcomeText">
              <h2 className="welcome">Welcome, Administrator</h2>
              <div ref={(el) => (text = el)} className="text">
                <p className="bodyText">
                  You may visit the Workout Catalog to{" "}
                  <span className="enable" ref={(el) => (edit = el)}>
                    EDIT
                  </span>{" "}
                  or{" "}
                  <span className="disable" ref={(el) => (disable = el)}>
                    DISABLE
                  </span>{" "}
                  workouts. You can also
                  <span className="add" ref={(el) => (add = el)}>
                    {" "}
                    ADD
                  </span>{" "}
                  a workout.
                </p>
              </div>
              <div col s7 push-s5 ref={(el) => (text2 = el)} className="text2">
                <p className="text2">Happy Administrating!</p>
              </div>
              <div className="adminImage">
                <img
                  ref={(el) => (img = el)}
                  className="responsive-img"
                  src="/admin-image.jpg"
                  alt=""
                />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminHome;