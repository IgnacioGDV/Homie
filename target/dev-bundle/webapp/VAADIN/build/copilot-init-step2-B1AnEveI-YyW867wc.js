var _a, _b;
import { B as ul, a as cl, k as $c, $, A, D as jc, W as Wc, d as dn, g as po, X as Xe$1, E as xc, G as Tc, p as p$1, P as Pl, b as Po, c as ct, H as Xa, O, e as du, w as qn, T as Te$1, j as Ce, J as Pc, L as eu, M as ue, N as Gc, S as le, U as Uc, V as fn, Y as ut, _ as Bc, a0 as zc, f as fu, n as iu, a1 as ou, a2 as au, I as Il, u as uu, a3 as wo, a4 as wc, a5 as mc, q as qc, a6 as ho } from "./indexhtml-DiL04ybs.js";
import { g, h } from "./state-CpHuw1-w-Bp2DjuZw.js";
import { v, u } from "./overlay-monkeypatch-C6Ef2C3V-So13HYcu.js";
import { r as r$1 } from "./icons-CenXfqbT-Bb6E__cS.js";
/**
 * @license
 * Copyright 2017 Google LLC
 * SPDX-License-Identifier: BSD-3-Clause
 */
function Le(e) {
  return (t, o) => {
    const i = typeof t == "function" ? t : t[o];
    Object.assign(i, e);
  };
}
const X = "@keyframes bounce{0%{transform:scale(.8)}50%{transform:scale(1.5)}to{transform:scale(1)}}@keyframes around-we-go-again{0%{background-position:0 0,0 0,calc(var(--glow-size) * -.5) calc(var(--glow-size) * -.5),calc(100% + calc(var(--glow-size) * .5)) calc(100% + calc(var(--glow-size) * .5))}25%{background-position:0 0,0 0,calc(100% + calc(var(--glow-size) * .5)) calc(var(--glow-size) * -.5),calc(var(--glow-size) * -.5) calc(100% + calc(var(--glow-size) * .5))}50%{background-position:0 0,0 0,calc(100% + calc(var(--glow-size) * .5)) calc(100% + calc(var(--glow-size) * .5)),calc(var(--glow-size) * -.5) calc(var(--glow-size) * -.5)}75%{background-position:0 0,0 0,calc(var(--glow-size) * -.5) calc(100% + calc(var(--glow-size) * .5)),calc(100% + calc(var(--glow-size) * .5)) calc(var(--glow-size) * -.5)}to{background-position:0 0,0 0,calc(var(--glow-size) * -.5) calc(var(--glow-size) * -.5),calc(100% + calc(var(--glow-size) * .5)) calc(100% + calc(var(--glow-size) * .5))}}@keyframes swirl{0%{rotate:0deg;filter:hue-rotate(20deg)}50%{filter:hue-rotate(-30deg)}to{rotate:360deg;filter:hue-rotate(20deg)}}";
var Ee = Object.defineProperty, Me = Object.getOwnPropertyDescriptor, S = (e, t, o, i) => {
  for (var n = i > 1 ? void 0 : i ? Me(t, o) : t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = (i ? r(t, o, n) : r(n)) || n);
  return i && n && Ee(t, o, n), n;
};
const J = "data-drag-initial-index", R = "data-drag-final-index";
let P = class extends Pl {
  constructor() {
    super(...arguments), this.position = "right", this.opened = false, this.keepOpen = false, this.resizing = false, this.closingForcefully = false, this.draggingSectionPanel = null, this.documentMouseUpListener = () => {
      this.resizing && A.emit("user-select", { allowSelection: true }), this.resizing = false, p$1.setDrawerResizing(false), this.removeAttribute("resizing");
    }, this.activationAnimationTransitionEndListener = () => {
      this.style.removeProperty("--closing-delay"), this.style.removeProperty("--initial-position"), this.removeEventListener("transitionend", this.activationAnimationTransitionEndListener);
    }, this.resizingMouseMoveListener = (e) => {
      if (!this.resizing)
        return;
      const { x: t, y: o } = e;
      e.stopPropagation(), e.preventDefault(), requestAnimationFrame(() => {
        let i;
        if (this.position === "right") {
          const n = document.body.clientWidth - t;
          this.style.setProperty("--size", `${n}px`), ue.saveDrawerSize(this.position, n), i = { width: n };
        } else if (this.position === "left") {
          const n = t;
          this.style.setProperty("--size", `${n}px`), ue.saveDrawerSize(this.position, n), i = { width: n };
        } else if (this.position === "bottom") {
          const n = document.body.clientHeight - o;
          this.style.setProperty("--size", `${n}px`), ue.saveDrawerSize(this.position, n), i = { height: n };
        }
        $.panels.filter((n) => !n.floating && n.panel === this.position).forEach((n) => {
          $.updatePanel(n.tag, i);
        });
      });
    }, this.sectionPanelDraggingStarted = (e, t) => {
      this.draggingSectionPanel = e, A.emit("user-select", { allowSelection: false }), this.draggingSectionPointerStartY = t.clientY, e.toggleAttribute("dragging", true), e.style.zIndex = "1000", Array.from(this.querySelectorAll("copilot-section-panel-wrapper")).forEach((o, i) => {
        o.setAttribute(J, `${i}`);
      }), document.addEventListener("mousemove", this.sectionPanelDragging), document.addEventListener("mouseup", this.sectionPanelDraggingFinished);
    }, this.sectionPanelDragging = (e) => {
      if (!this.draggingSectionPanel)
        return;
      const { clientX: t, clientY: o } = e;
      if (!Gc(this.getBoundingClientRect(), t, o)) {
        this.cleanUpDragging();
        return;
      }
      const i = o - this.draggingSectionPointerStartY;
      this.draggingSectionPanel.style.transform = `translateY(${i}px)`, this.updateSectionPanelPositionsWhileDragging();
    }, this.sectionPanelDraggingFinished = () => {
      if (!this.draggingSectionPanel)
        return;
      A.emit("user-select", { allowSelection: true });
      const e = this.getAllPanels().filter(
        (t) => {
          var _a2;
          return t.hasAttribute(R) && ((_a2 = t.panelInfo) == null ? void 0 : _a2.panelOrder) !== Number.parseInt(t.getAttribute(R), 10);
        }
      ).map((t) => ({
        tag: t.panelTag,
        order: Number.parseInt(t.getAttribute(R), 10)
      }));
      this.cleanUpDragging(), $.updateOrders(e), document.removeEventListener("mouseup", this.sectionPanelDraggingFinished), document.removeEventListener("mousemove", this.sectionPanelDragging);
    }, this.updateSectionPanelPositionsWhileDragging = () => {
      const e = this.draggingSectionPanel.getBoundingClientRect().height;
      this.getAllPanels().sort((t, o) => {
        const i = t.getBoundingClientRect(), n = o.getBoundingClientRect(), a = (i.top + i.bottom) / 2, r = (n.top + n.bottom) / 2;
        return a - r;
      }).forEach((t, o) => {
        var _a2;
        if (t.setAttribute(R, `${o}`), t.panelTag !== ((_a2 = this.draggingSectionPanel) == null ? void 0 : _a2.panelTag)) {
          const i = Number.parseInt(t.getAttribute(J), 10);
          i > o ? t.style.transform = `translateY(${-e}px)` : i < o ? t.style.transform = `translateY(${e}px)` : t.style.removeProperty("transform");
        }
      });
    };
  }
  static get styles() {
    return [
      le(X),
      ul`
        :host {
          --size: 350px;
          --min-size: 20%;
          --max-size: 80%;
          --default-content-height: 300px;
          --transition-duration: var(--duration-2);
          --opening-delay: var(--duration-2);
          --closing-delay: var(--duration-3);
          --hover-size: 18px;
          --initial-position: 0px;
          position: absolute;
          z-index: var(--z-index-drawer);
          transition: translate var(--transition-duration) var(--closing-delay);
        }

        :host([no-transition]),
        :host([no-transition]) .container {
          transition: none;
          -webkit-transition: none;
          -moz-transition: none;
          -o-transition: none;
        }

        :host(:is([position='left'], [position='right'])) {
          width: var(--size);
          min-width: var(--min-size);
          max-width: var(--max-size);
          top: 0;
          bottom: 0;
        }

        :host([position='left']) {
          left: var(--initial-position);
          translate: calc(-100% + var(--hover-size)) 0%;
          padding-right: var(--hover-size);
        }

        :host([position='right']) {
          right: var(--initial-position);
          translate: calc(100% - var(--hover-size)) 0%;
          padding-left: var(--hover-size);
        }

        :host([position='bottom']) {
          height: var(--size);
          min-height: var(--min-size);
          max-height: var(--max-size);
          bottom: var(--initial-position);
          left: 0;
          right: 0;
          translate: 0% calc(100% - var(--hover-size));
          padding-top: var(--hover-size);
        }

        /* The visible container. Needed to have extra space for hover and resize handle outside it. */

        .container {
          display: flex;
          flex-direction: column;
          box-sizing: border-box;
          height: 100%;
          background: var(--surface);
          -webkit-backdrop-filter: var(--surface-backdrop-filter);
          backdrop-filter: var(--surface-backdrop-filter);
          overflow-y: auto;
          overflow-x: hidden;
          box-shadow: var(--surface-box-shadow-2);
          transition:
            opacity var(--transition-duration) var(--closing-delay),
            visibility calc(var(--transition-duration) * 2) var(--closing-delay);
          opacity: 0;
          /* For accessibility (restored when open) */
          visibility: hidden;
        }

        :host([position='left']) .container {
          border-right: 1px solid var(--surface-border-color);
        }

        :host([position='right']) .container {
          border-left: 1px solid var(--surface-border-color);
        }

        :host([position='bottom']) .container {
          border-top: 1px solid var(--surface-border-color);
        }

        /* Opened state */

        :host(:is([opened], [keepopen])) {
          translate: 0% 0%;
          transition-delay: var(--opening-delay);
          z-index: var(--z-index-opened-drawer);
        }

        :host(:is([opened], [keepopen])) .container {
          transition-delay: var(--opening-delay);
          visibility: visible;
          opacity: 1;
        }

        .drawer-indicator {
          align-items: center;
          border-radius: 9999px;
          box-shadow: inset 0 0 0 1px hsl(0 0% 0% / 0.2);
          color: white;
          display: flex;
          height: 1.75rem;
          justify-content: center;
          overflow: hidden;
          opacity: 1;
          position: absolute;
          transition-delay: 0.5s;
          transition-duration: 0.2s;
          transition-property: opacity;
          width: 1.75rem;
        }

        .drawer-indicator::before {
          animation: 5s swirl linear infinite;
          animation-play-state: running;
          background-image: radial-gradient(circle at 50% -10%, hsl(221 100% 55% / 0.6) 0%, transparent 60%),
            radial-gradient(circle at 25% 40%, hsl(303 71% 64%) 0%, transparent 70%),
            radial-gradient(circle at 80% 10%, hsla(262, 38%, 9%, 0.5) 0%, transparent 80%),
            radial-gradient(circle at 110% 50%, hsla(147, 100%, 77%, 1) 20%, transparent 100%);
          content: '';
          inset: 0;
          opacity: 1;
          position: absolute;
          transition: opacity 0.5s;
        }
        :host([attention-required]) .drawer-indicator::before {
          background-image: radial-gradient(circle at 50% -10%, hsl(0deg 100% 55% / 60%) 0%, transparent 60%),
            radial-gradient(circle at 25% 40%, hsl(0deg 71% 64%) 0%, transparent 70%),
            radial-gradient(circle at 80% 10%, hsl(0deg 38% 9% / 50%) 0%, transparent 80%),
            radial-gradient(circle at 110% 50%, hsl(0deg 100% 77%) 20%, transparent 100%);
        }
        :host([opened]) .drawer-indicator {
          opacity: 0;
          transition-delay: 0s;
        }

        .drawer-indicator svg {
          height: 0.75rem;
          width: 0.75rem;
          z-index: 1;
        }

        :host([position='right']) .drawer-indicator {
          left: 0.25rem;
          top: calc(50% - 0.875rem);
        }

        :host([position='right']) .drawer-indicator svg {
          margin-inline-start: -0.625rem;
          transform: rotate(-90deg);
        }

        :host([position='left']) .drawer-indicator {
          right: 0.25rem;
          top: calc(50% - 0.875rem);
        }

        :host([position='left']) .drawer-indicator svg {
          margin-inline-end: -0.625rem;
          transform: rotate(90deg);
        }

        :host([position='bottom']) .drawer-indicator {
          left: calc(50% - 0.875rem);
          top: 0.25rem;
        }

        :host([position='bottom']) .drawer-indicator svg {
          margin-top: -0.625rem;
        }

        .overflow-indicator {
          align-items: center;
          border-radius: 9999px;
          bottom: -0.875rem;
          box-shadow: inset 0 0 0 1px hsl(0 0% 0% / 0.2);
          color: white;
          display: flex;
          height: 1.75rem;
          justify-content: center;
          left: calc(50% - 0.875rem);
          overflow: hidden;
          opacity: 0;
          position: absolute;
          transition: opacity 0.2s;
          width: 1.75rem;
        }

        .overflow-indicator::after {
          background: hsl(0 0% 0% / 0.5);
          border-radius: 9999px;
          content: '';
          inset: 2px;
          opacity: 1;
          position: absolute;
        }

        .overflow-indicator::before {
          animation: 5s swirl linear infinite;
          animation-play-state: running;
          background-image: radial-gradient(circle at 50% -10%, hsl(221 100% 55% / 0.6) 0%, transparent 60%),
            radial-gradient(circle at 25% 40%, hsl(303 71% 64%) 0%, transparent 70%),
            radial-gradient(circle at 80% 10%, hsla(262, 38%, 9%, 0.5) 0%, transparent 80%),
            radial-gradient(circle at 110% 50%, hsla(147, 100%, 77%, 1) 20%, transparent 100%);
          content: '';
          inset: 0;
          opacity: 1;
          position: absolute;
          transition: opacity 0.5s;
        }

        .overflow-indicator svg {
          height: 0.75rem;
          margin-top: -0.625rem;
          width: 0.75rem;
          z-index: 1;
        }

        :host([position='left']) [canscroll] .overflow-indicator,
        :host([position='right']) [canscroll] .overflow-indicator {
          opacity: 1;
        }

        .resize {
          position: absolute;
          z-index: 10;
          inset: 0;
        }

        :host(:is([position='left'], [position='right'])) .resize {
          width: var(--hover-size);
          cursor: col-resize;
        }

        :host([position='left']) .resize {
          left: auto;
          right: calc(var(--hover-size) * 0.5);
        }

        :host([position='right']) .resize {
          right: auto;
          left: calc(var(--hover-size) * 0.5);
        }

        :host([position='bottom']) .resize {
          height: var(--hover-size);
          bottom: auto;
          top: calc(var(--hover-size) * 0.5);
          cursor: row-resize;
        }

        :host([resizing]) .container {
          /* vaadin-grid (used in the outline) blocks the mouse events */
          pointer-events: none;
        }

        /* Visual indication of the drawer */

        :host::before {
          content: '';
          position: absolute;
          pointer-events: none;
          z-index: -1;
          inset: var(--hover-size);
          transition: opacity var(--transition-duration) var(--closing-delay);
        }

        :host([document-hidden])::before {
          animation: none;
        }

        :host([document-hidden]) .drawer-indicator {
          -webkit-filter: grayscale(100%); /* Chrome, Safari, Opera */
          filter: grayscale(100%);
        }

        :host(:is([opened], [keepopen]))::before {
          transition-delay: var(--opening-delay);
          opacity: 0;
        }

        .hasmoreContainer {
          height: 100%;
          position: relative;
        }
      `
    ];
  }
  connectedCallback() {
    super.connectedCallback(), this.reaction(
      () => $.panels,
      () => this.requestUpdate()
    ), this.reaction(
      () => p$1.operationInProgress,
      (t) => {
        t === eu.DragAndDrop && !this.opened && !this.keepOpen ? this.style.setProperty("pointer-events", "none") : this.style.setProperty("pointer-events", "auto");
      }
    ), this.reaction(
      () => $.getAttentionRequiredPanelConfiguration(),
      () => {
        const t = $.getAttentionRequiredPanelConfiguration();
        t && !t.floating && this.toggleAttribute(Uc, t.panel === this.position);
      }
    ), this.reaction(
      () => p$1.active,
      () => {
        if (!p$1.active || !fn.isActivationAnimation() || p$1.activatedFrom === "restore" || p$1.activatedFrom === "test")
          return;
        const t = $.getAttentionRequiredPanelConfiguration();
        t && !t.floating && t.panel === this.position || (this.addEventListener("transitionend", this.activationAnimationTransitionEndListener), this.toggleAttribute("no-transition", true), this.opened = true, this.style.setProperty("--closing-delay", "var(--duration-1)"), this.style.setProperty("--initial-position", "calc(-1 * (max(var(--size), var(--min-size)) * 1) / 3)"), requestAnimationFrame(() => {
          this.toggleAttribute("no-transition", false), this.opened = false;
        }));
      }
    ), document.addEventListener("mouseup", this.documentMouseUpListener);
    const e = ue.getDrawerSize(this.position);
    e && this.style.setProperty("--size", `${e}px`), document.addEventListener("mousemove", this.resizingMouseMoveListener), this.addEventListener("mouseenter", this.mouseEnterListener), A.on("document-activation-change", (t) => {
      this.toggleAttribute("document-hidden", !t.detail.active);
    });
  }
  firstUpdated(e) {
    super.firstUpdated(e), requestAnimationFrame(() => this.toggleAttribute("no-transition", false)), this.resizeElement.addEventListener("mousedown", (t) => {
      t.button === 0 && (this.resizing = true, p$1.setDrawerResizing(true), this.setAttribute("resizing", ""), A.emit("user-select", { allowSelection: false }));
    });
  }
  updated(e) {
    super.updated(e), e.has("opened") && this.opened && this.hasAttribute(Uc) && (this.removeAttribute(Uc), $.clearAttention()), this.updateScrollable();
  }
  disconnectedCallback() {
    super.disconnectedCallback(), document.removeEventListener("mousemove", this.resizingMouseMoveListener), document.removeEventListener("mouseup", this.documentMouseUpListener), this.removeEventListener("mouseenter", this.mouseEnterListener);
  }
  /**
   * Cleans up attributes/styles etc... for dragging operations
   * @private
   */
  cleanUpDragging() {
    this.draggingSectionPanel && (p$1.setSectionPanelDragging(false), this.draggingSectionPanel.style.zIndex = "", Array.from(this.querySelectorAll("copilot-section-panel-wrapper")).forEach((e) => {
      e.style.removeProperty("transform"), e.removeAttribute(R), e.removeAttribute(J);
    }), this.draggingSectionPanel.removeAttribute("dragging"), this.draggingSectionPanel = null);
  }
  getAllPanels() {
    return Array.from(this.querySelectorAll("copilot-section-panel-wrapper"));
  }
  /**
   * Closes the drawer and disables mouse enter event for a while.
   */
  forceClose() {
    this.closingForcefully = true, this.opened = false, setTimeout(() => {
      this.closingForcefully = false;
    }, 0.5);
  }
  mouseEnterListener(e) {
    if (this.closingForcefully || p$1.sectionPanelResizing)
      return;
    document.querySelector("copilot-main").shadowRoot.querySelector("copilot-drawer-panel[opened]") || (this.opened = true);
  }
  render() {
    return ct`
      <div class="hasmoreContainer">
        <div class="container" @scroll=${this.updateScrollable}>
          <slot></slot>
        </div>
        <div class="overflow-indicator">${r$1.chevronDown}</div>
      </div>
      <div class="resize"></div>
      <div class="drawer-indicator">${r$1.chevronUp}</div>
    `;
  }
  updateScrollable() {
    this.hasmoreContainer.toggleAttribute(
      "canscroll",
      this.container.scrollHeight - this.container.scrollTop - this.container.clientHeight > 10
    );
  }
};
S([
  h({ reflect: true, attribute: true })
], P.prototype, "position", 2);
S([
  h({ reflect: true, type: Boolean })
], P.prototype, "opened", 2);
S([
  h({ reflect: true, type: Boolean })
], P.prototype, "keepOpen", 2);
S([
  v(".container")
], P.prototype, "container", 2);
S([
  v(".hasmoreContainer")
], P.prototype, "hasmoreContainer", 2);
S([
  v(".resize")
], P.prototype, "resizeElement", 2);
S([
  Le({ passive: true })
], P.prototype, "updateScrollable", 1);
P = S([
  cl("copilot-drawer-panel")
], P);
var Re = Object.defineProperty, Oe = Object.getOwnPropertyDescriptor, he = (e, t, o, i) => {
  for (var n = i > 1 ? void 0 : i ? Oe(t, o) : t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = (i ? r(t, o, n) : r(n)) || n);
  return i && n && Re(t, o, n), n;
};
let F = class extends ut {
  constructor() {
    super(...arguments), this.checked = false;
  }
  static get styles() {
    return ul`
      .switch {
        display: inline-flex;
        align-items: center;
        gap: var(--space-100);
      }

      .switch input {
        height: 0;
        opacity: 0;
        width: 0;
      }

      .slider {
        background-color: var(--gray-300);
        border-radius: 9999px;
        cursor: pointer;
        inset: 0;
        position: absolute;
        transition: 0.4s;
        height: 0.75rem;
        position: relative;
        width: 1.5rem;
        min-width: 1.5rem;
      }

      .slider:before {
        background-color: white;
        border-radius: 50%;
        bottom: 1px;
        content: '';
        height: 0.625rem;
        left: 1px;
        position: absolute;
        transition: 0.4s;
        width: 0.625rem;
      }

      input:checked + .slider {
        background-color: var(--selection-color);
      }

      input:checked + .slider:before {
        transform: translateX(0.75rem);
      }

      label:has(input:focus) {
        outline: 2px solid var(--selection-color);
        outline-offset: 2px;
      }
    `;
  }
  render() {
    return ct`
      <label class="switch">
        <input
          class="feature-toggle"
          id="feature-toggle-${this.id}"
          type="checkbox"
          ?checked="${this.checked}"
          @change=${(e) => {
      e.preventDefault(), this.checked = e.target.checked, this.dispatchEvent(new CustomEvent("on-change"));
    }} />
        <span class="slider"></span>
        ${this.title}
      </label>
    `;
  }
  //  @change=${(e: InputEvent) => this.toggleFeatureFlag(e, feature)}
};
he([
  h({ reflect: true, type: Boolean })
], F.prototype, "checked", 2);
F = he([
  cl("copilot-toggle-button")
], F);
function f(e, t) {
  const o = document.createElement(e);
  if (t.style && (o.className = t.style), t.icon)
    if (typeof t.icon == "string") {
      const i = document.createElement("vaadin-icon");
      i.setAttribute("icon", t.icon), o.append(i);
    } else
      o.append(_e(t.icon.strings[0]));
  if (t.label) {
    const i = document.createElement("span");
    i.className = "label", i.innerHTML = t.label, o.append(i);
  }
  if (t.hint) {
    const i = document.createElement("span");
    i.className = "hint", i.innerHTML = t.hint, o.append(i);
  }
  return o;
}
function _e(e) {
  if (!e) return null;
  const t = document.createElement("template");
  t.innerHTML = e;
  const o = t.content.children;
  return o.length === 1 ? o[0] : o;
}
class je {
  constructor() {
    this.offsetX = 0, this.offsetY = 0;
  }
  draggingStarts(t, o) {
    this.offsetX = o.clientX - t.getBoundingClientRect().left, this.offsetY = o.clientY - t.getBoundingClientRect().top;
  }
  dragging(t, o) {
    const i = o.clientX, n = o.clientY, a = i - this.offsetX, r = i - this.offsetX + t.getBoundingClientRect().width, h2 = n - this.offsetY, g2 = n - this.offsetY + t.getBoundingClientRect().height;
    return this.adjust(t, a, h2, r, g2);
  }
  adjust(t, o, i, n, a) {
    let r, h2, g2, v2;
    const y = document.documentElement.getBoundingClientRect().width, U = document.documentElement.getBoundingClientRect().height;
    return (n + o) / 2 < y / 2 ? (t.style.setProperty("--left", `${o}px`), t.style.setProperty("--right", ""), v2 = void 0, r = Math.max(0, o)) : (t.style.removeProperty("--left"), t.style.setProperty("--right", `${y - n}px`), r = void 0, v2 = Math.max(0, y - n)), (i + a) / 2 < U / 2 ? (t.style.setProperty("--top", `${i}px`), t.style.setProperty("--bottom", ""), g2 = void 0, h2 = Math.max(0, i)) : (t.style.setProperty("--top", ""), t.style.setProperty("--bottom", `${U - a}px`), h2 = void 0, g2 = Math.max(0, U - a)), {
      left: r,
      right: v2,
      top: h2,
      bottom: g2
    };
  }
  anchor(t) {
    const { left: o, top: i, bottom: n, right: a } = t.getBoundingClientRect();
    return this.adjust(t, o, i, a, n);
  }
  anchorLeftTop(t) {
    const { left: o, top: i } = t.getBoundingClientRect();
    return t.style.setProperty("--left", `${o}px`), t.style.setProperty("--right", ""), t.style.setProperty("--top", `${i}px`), t.style.setProperty("--bottom", ""), {
      left: o,
      top: i
    };
  }
}
const x = new je();
var Te = Object.defineProperty, He = Object.getOwnPropertyDescriptor, M = (e, t, o, i) => {
  for (var n = i > 1 ? void 0 : i ? He(t, o) : t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = (i ? r(t, o, n) : r(n)) || n);
  return i && n && Te(t, o, n), n;
};
const Z = "https://github.com/JetBrains/JetBrainsRuntime/releases";
function Be(e, t) {
  if (!t)
    return true;
  const [o, i, n] = t.split(".").map((g2) => parseInt(g2)), [a, r, h2] = e.split(".").map((g2) => parseInt(g2));
  if (o < a)
    return true;
  if (o == a) {
    if (i < r)
      return true;
    if (i === r)
      return n < h2;
  }
  return false;
}
const Q = "Download complete";
let w = class extends Pl {
  constructor() {
    super(), this.javaPluginSectionOpened = false, this.hotswapSectionOpened = false, this.hotswapTab = "hotswapagent", this.downloadStatusMessages = [], this.downloadProgress = 0, this.onDownloadStatusUpdate = this.downloadStatusUpdate.bind(this), this.reaction(
      () => [p$1.jdkInfo, p$1.idePluginState],
      () => {
        p$1.idePluginState && (!p$1.idePluginState.ide || !p$1.idePluginState.active ? this.javaPluginSectionOpened = true : (!(/* @__PURE__ */ new Set(["vscode", "intellij"])).has(p$1.idePluginState.ide) || !p$1.idePluginState.active) && (this.javaPluginSectionOpened = false)), p$1.jdkInfo && Po() !== "success" && (this.hotswapSectionOpened = true);
      },
      { fireImmediately: true }
    );
  }
  connectedCallback() {
    super.connectedCallback(), A.on("set-up-vs-code-hotswap-status", this.onDownloadStatusUpdate);
  }
  disconnectedCallback() {
    super.disconnectedCallback(), A.off("set-up-vs-code-hotswap-status", this.onDownloadStatusUpdate);
  }
  render() {
    var _a2, _b2, _c, _d;
    const e = {
      intellij: ((_a2 = p$1.idePluginState) == null ? void 0 : _a2.ide) === "intellij",
      vscode: ((_b2 = p$1.idePluginState) == null ? void 0 : _b2.ide) === "vscode",
      eclipse: ((_c = p$1.idePluginState) == null ? void 0 : _c.ide) === "eclipse",
      idePluginInstalled: !!((_d = p$1.idePluginState) == null ? void 0 : _d.active)
    };
    return ct`
      <div part="container">${this.renderPluginSection(e)} ${this.renderHotswapSection(e)}</div>
      <div part="footer">
        <vaadin-button
          id="close"
          @click="${() => $.updatePanel(Y.tag, { floating: false })}"
          >Close
        </vaadin-button>
      </div>
    `;
  }
  renderPluginSection(e) {
    let t = "";
    e.intellij ? t = "IntelliJ" : e.vscode ? t = "VS Code" : e.eclipse && (t = "Eclipse");
    let o, i;
    e.vscode || e.intellij ? e.idePluginInstalled ? (o = `Plugin for ${t} installed`, i = this.renderPluginInstalledContent()) : (o = `Plugin for ${t} not installed`, i = this.renderPluginIsNotInstalledContent(e)) : e.eclipse ? (o = "Eclipse development workflow is not supported yet", i = this.renderEclipsePluginContent()) : (o = "No IDE found", i = this.renderNoIdePluginContent());
    const n = e.idePluginInstalled ? r$1.successColorful : r$1.warningColorful;
    return ct`
      <details
        part="panel"
        .open=${this.javaPluginSectionOpened}
        @toggle=${(a) => {
      Xa(() => {
        this.javaPluginSectionOpened = a.target.open;
      });
    }}>
        <summary part="header">
          <span class="icon">${n}</span>
          <div>${o}</div>
        </summary>
        <div part="content">${i}</div>
      </details>
    `;
  }
  renderNoIdePluginContent() {
    return ct`
      <div>
        <div>We could not detect an IDE</div>
        ${this.recommendSupportedPlugin()}
      </div>
    `;
  }
  renderEclipsePluginContent() {
    return ct`
      <div>
        <div>Eclipse workflow environment is not supported yet.</div>
        ${this.recommendSupportedPlugin()}
      </div>
    `;
  }
  recommendSupportedPlugin() {
    return ct`<p>
      Please use <a href="https://code.visualstudio.com">Visual Studio Code</a> or
      <a href="https://www.jetbrains.com/idea">IntelliJ IDEA</a> for better development experience
    </p>`;
  }
  renderPluginInstalledContent() {
    return ct` <p>You have a running plugin. Enjoy your awesome development workflow!</p> `;
  }
  renderPluginIsNotInstalledContent(e) {
    let t = null, o = "Install from Marketplace";
    return e.intellij ? (t = Bc, o = "Install from JetBrains Marketplace") : e.vscode && (t = zc, o = "Install from VSCode Marketplace"), ct`
      <div>
        <div>Install the Vaadin IDE Plugin to ensure a smooth development workflow</div>
        <p>
          Installing the plugin is not required, but strongly recommended.<br />Some Vaadin Copilot functionality, such
          as undo, will not function optimally without the plugin.
        </p>
        ${t ? ct` <div>
              <vaadin-button
                @click="${() => {
      window.open(t, "_blank");
    }}"
                >${o}
                <vaadin-icon icon="vaadin:external-link"></vaadin-icon>
              </vaadin-button>
            </div>` : O}
      </div>
    `;
  }
  renderHotswapSection(e) {
    const { jdkInfo: t } = p$1;
    if (!t)
      return O;
    const o = Po(), i = du();
    let n, a, r;
    return o === "success" ? (n = r$1.successColorful, r = "Java Hotswap is enabled") : o === "warning" ? (n = r$1.warningColorful, r = "Java Hotswap is not enabled") : o === "error" && (n = r$1.warningColorful, r = "Java Hotswap is partially enabled"), this.hotswapTab === "jrebel" ? t.jrebel ? a = this.renderJRebelInstalledContent() : a = this.renderJRebelNotInstalledContent() : e.intellij ? a = this.renderHotswapAgentIntelliJPluginContent() : a = this.renderHotswapAgentNotInstalledContent(e), ct` <details
      part="panel"
      .open=${this.hotswapSectionOpened}
      @toggle=${(h2) => {
      Xa(() => {
        this.hotswapSectionOpened = h2.target.open;
      });
    }}>
      <summary part="header">
        <span class="icon">${n}</span>
        <div>${r}</div>
      </summary>
      <div part="content">
        ${i !== "none" ? ct`${i == "jrebel" ? this.renderJRebelInstalledContent() : this.renderHotswapAgentInstalledContent()}` : ct`
            <div class="tabs" role="tablist">
              <button
                aria-selected="${this.hotswapTab === "hotswapagent" ? "true" : "false"}"
                class="tab"
                role="tab"
                @click=${() => {
      this.hotswapTab = "hotswapagent";
    }}>
                Hotswap Agent
              </button>
              <button
                aria-selected="${this.hotswapTab === "jrebel" ? "true" : "false"}"
                class="tab"
                role="tab"
                @click=${() => {
      this.hotswapTab = "jrebel";
    }}>
                JRebel
              </button>
            </div>
            <div part="content">${a}</div>
            </div>
            </details>
          `}
      </div>
    </details>`;
  }
  renderJRebelNotInstalledContent() {
    return ct`
      <div>
        <a href="https://www.jrebel.com">JRebel ${r$1.linkExternal}</a> is a commercial hotswap solution. Vaadin
        detects the JRebel Agent and automatically reloads the application in the browser after the Java changes have
        been hotpatched.
        <p>
          Go to
          <a href="https://www.jrebel.com/products/jrebel/learn" target="_blank" rel="noopener noreferrer">
            https://www.jrebel.com/products/jrebel/learn ${r$1.linkExternal}</a
          >
          to get started
        </p>
      </div>
    `;
  }
  renderHotswapAgentNotInstalledContent(e) {
    const t = [
      this.renderJavaRunningInDebugModeSection(),
      this.renderHotswapAgentJdkSection(e),
      this.renderInstallHotswapAgentJdkSection(e),
      this.renderHotswapAgentVersionSection(),
      this.renderHotswapAgentMissingArgParam(e)
    ];
    return ct` <div part="hotswap-agent-section-container">${t}</div> `;
  }
  renderHotswapAgentIntelliJPluginContent() {
    const t = Po() === "success";
    return ct`
      <div part="hotswap-agent-section-container">
        <div class="inner-section">
          <details class="inner" .open="${!t}">
            <summary>
              <span class="icon">${t ? r$1.successColorful : r$1.warningColorful}</span>
              <div>Use 'Debug using Hotswap Agent' launch configuration</div>
            </summary>
            <div class="hint">
              Vaadin IntelliJ plugin offers launch mode that does not require any manual configuration!
              <p>
                In order to run recommended launch configuration, you should click three dots right next to Debug button
                and select <code>Debug using Hotswap Agent</code> option.
              </p>
            </div>
          </details>
        </div>
      </div>
    `;
  }
  renderJavaRunningInDebugModeSection() {
    var _a2;
    const e = (_a2 = p$1.jdkInfo) == null ? void 0 : _a2.runningInJavaDebugMode;
    return ct`
      <div class="inner-section">
        <details class="inner" .open="${!e}">
          <summary>
            <span class="icon">${e ? r$1.successColorful : r$1.warningColorful}</span>
            <div>Run Java in debug mode</div>
          </summary>
          <div class="hint">Start the application in debug mode in the IDE</div>
        </details>
      </div>
    `;
  }
  renderHotswapAgentMissingArgParam(e) {
    var _a2, _b2;
    const t = ((_a2 = p$1.jdkInfo) == null ? void 0 : _a2.runningWitHotswap) && ((_b2 = p$1.jdkInfo) == null ? void 0 : _b2.runningWithExtendClassDef);
    return ct`
      <div class="inner-section">
        <details class="inner" .open="${!t}">
          <summary>
            <span class="icon">${t ? r$1.successColorful : r$1.warningColorful}</span>
            <div>Enable HotswapAgent</div>
          </summary>
          <div class="hint">
            <ul>
              ${e.intellij ? ct`<li>Launch as mentioned in the previous step</li>` : O}
              ${e.intellij ? ct`<li>
                    To manually configure IntelliJ, add the
                    <code>-XX:HotswapAgent=fatjar -XX:+AllowEnhancedClassRedefinition -XX:+UpdateClasses</code> JVM
                    arguments when launching the application
                  </li>` : ct`<li>
                    Add the
                    <code>-XX:HotswapAgent=fatjar -XX:+AllowEnhancedClassRedefinition -XX:+UpdateClasses</code> JVM
                    arguments when launching the application
                  </li>`}
            </ul>
          </div>
        </details>
      </div>
    `;
  }
  renderHotswapAgentJdkSection(e) {
    var _a2, _b2, _c;
    const t = (_a2 = p$1.jdkInfo) == null ? void 0 : _a2.extendedClassDefCapable, o = ((_b2 = this.downloadStatusMessages) == null ? void 0 : _b2[this.downloadStatusMessages.length - 1]) === Q;
    return ct`
      <div class="inner-section">
        <details class="inner" .open="${!t}">
          <summary>
            <span class="icon">${t ? r$1.successColorful : r$1.warningColorful}</span>
            <div>Run using JetBrains Runtime JDK</div>
          </summary>
          <div class="hint">
            JetBrains Runtime provides much better hotswapping compared to other JDKs.
            <ul>
              ${e.intellij && Be("1.3.0", (_c = p$1.idePluginState) == null ? void 0 : _c.version) ? ct` <li>Upgrade to the latest IntelliJ plugin</li>` : O}
              ${e.intellij ? ct` <li>Launch the application in IntelliJ using "Debug using Hotswap Agent"</li>` : O}
              ${e.vscode ? ct` <li>
                    <a href @click="${(i) => this.downloadJetbrainsRuntime(i)}"
                      >Let Copilot download and set up JetBrains Runtime for VS Code</a
                    >
                    ${this.downloadProgress > 0 ? ct`<vaadin-progress-bar
                          .value="${this.downloadProgress}"
                          min="0"
                          max="1"></vaadin-progress-bar>` : O}
                    <ul>
                      ${this.downloadStatusMessages.map((i) => ct`<li>${i}</li>`)}
                      ${o ? ct`<h3>Go to VS Code and launch the 'Debug using Hotswap Agent' configuration</h3>` : O}
                    </ul>
                  </li>` : O}
              <li>
                ${e.intellij || e.vscode ? ct`If there is a problem, you can manually
                      <a target="_blank" href="${Z}">download JetBrains Runtime JDK</a> and set up
                      your debug configuration to use it.` : ct`<a target="_blank" href="${Z}">Download JetBrains Runtime JDK</a> and set up
                      your debug configuration to use it.`}
              </li>
            </ul>
          </div>
        </details>
      </div>
    `;
  }
  renderInstallHotswapAgentJdkSection(e) {
    var _a2, _b2;
    const t = (_a2 = p$1.jdkInfo) == null ? void 0 : _a2.hotswapAgentFound, o = (_b2 = p$1.jdkInfo) == null ? void 0 : _b2.extendedClassDefCapable;
    return ct`
      <div class="inner-section">
        <details class="inner" .open="${!t}">
          <summary>
            <span class="icon">${t ? r$1.successColorful : r$1.warningColorful}</span>
            <div>Install HotswapAgent</div>
          </summary>
          <div class="hint">
            Hotswap Agent provides application level support for hot reloading, such as reinitalizing Vaadin @Route or
            @BrowserCallable classes when they are updated
            <ul>
              ${e.intellij ? ct`<li>Launch as mentioned in the previous step</li>` : O}
              ${!e.intellij && !o ? ct`<li>First install JetBrains Runtime as mentioned in the step above.</li>` : O}
              ${e.intellij ? ct`<li>
                    To manually configure IntelliJ, download HotswapAgent and install the jar file as
                    <code>[JAVA_HOME]/lib/hotswap/hotswap-agent.jar</code> in the JetBrains Runtime JDK. Note that the
                    file must be renamed to exactly match this path.
                  </li>` : ct`<li>
                    Download HotswapAgent and install the jar file as
                    <code>[JAVA_HOME]/lib/hotswap/hotswap-agent.jar</code> in the JetBrains Runtime JDK. Note that the
                    file must be renamed to exactly match this path.
                  </li>`}
            </ul>
          </div>
        </details>
      </div>
    `;
  }
  renderHotswapAgentVersionSection() {
    var _a2, _b2, _c, _d;
    if (!((_a2 = p$1.jdkInfo) == null ? void 0 : _a2.hotswapAgentFound))
      return O;
    const e = (_b2 = p$1.jdkInfo) == null ? void 0 : _b2.hotswapVersionOk, t = (_c = p$1.jdkInfo) == null ? void 0 : _c.hotswapVersion, o = (_d = p$1.jdkInfo) == null ? void 0 : _d.hotswapAgentLocation;
    return ct`
      <div class="inner-section">
        <details class="inner" .open="${!e}">
          <summary>
            <span class="icon">${e ? r$1.successColorful : r$1.warningColorful}</span>
            <div>Hotswap version requires update</div>
          </summary>
          <div class="hint">
            HotswapAgent version ${t} is in use
            <a target="_blank" href="https://github.com/HotswapProjects/HotswapAgent/releases"
              >Download the latest HotswapAgent</a
            >
            and place it in <code>${o}</code>
          </div>
        </details>
      </div>
    `;
  }
  renderJRebelInstalledContent() {
    return ct` <div>JRebel is in use. Enjoy your awesome development workflow!</div> `;
  }
  renderHotswapAgentInstalledContent() {
    return ct` <div>Hotswap agent is in use. Enjoy your awesome development workflow!</div> `;
  }
  async downloadJetbrainsRuntime(e) {
    return e.target.disabled = true, e.preventDefault(), this.downloadStatusMessages = [], qn(`${Ce}set-up-vs-code-hotswap`, {}, (t) => {
      t.data.error ? (Te$1("Error downloading JetBrains runtime", t.data.error), this.downloadStatusMessages = [...this.downloadStatusMessages, "Download failed"]) : this.downloadStatusMessages = [...this.downloadStatusMessages, Q];
    });
  }
  downloadStatusUpdate(e) {
    const t = e.detail.progress;
    t ? this.downloadProgress = t : this.downloadStatusMessages = [...this.downloadStatusMessages, e.detail.message];
  }
};
w.NAME = "copilot-development-setup-user-guide";
w.styles = ul`
    :host {
      --icon-size: 24px;
      --summary-header-gap: 10px;
      --footer-height: calc(50px + var(--space-150));
      color: var(--color);
    }
    :host code {
      background-color: var(--gray-50);
      font-size: var(--font-size-0);
      display: inline-block;
      margin-top: var(--space-100);
      margin-bottom: var(--space-100);
      user-select: all;
    }

    [part='container'] {
      display: flex;
      flex-direction: column;
      gap: var(--space-150);
      padding: var(--space-150);
      box-sizing: border-box;
      height: calc(100% - var(--footer-height));
      overflow: auto;
    }

    [part='footer'] {
      display: flex;
      justify-content: flex-end;
      height: var(--footer-height);
      padding-left: var(--space-150);
      padding-right: var(--space-150);
    }
    [part='hotswap-agent-section-container'] {
      display: flex;
      flex-direction: column;
      gap: var(--space-100);
    }
    [part='content'] {
      display: flex;
      padding: var(--space-150);
      flex-direction: column;
    }
    div.inner-section div.hint {
      margin-left: calc(var(--summary-header-gap) + var(--icon-size));
      margin-top: var(--space-75);
    }
    details {
      display: flex;
      flex-direction: column;
      box-sizing: border-box;

      & > summary span.icon {
        width: var(--icon-size);
        height: var(--icon-size);
      }
      & > summary,
      summary::part(header) {
        display: flex;
        flex-direction: row;
        align-items: center;
        cursor: pointer;
        position: relative;
        gap: var(--summary-header-gap);
        font: var(--font);
      }
      summary::after,
      summary::part(header)::after {
        content: '';
        display: block;
        width: 4px;
        height: 4px;
        border-color: var(--color);
        opacity: var(--panel-toggle-opacity, 0.2);
        border-width: 2px;
        border-style: solid solid none none;
        transform: rotate(var(--panel-toggle-angle, -45deg));
        position: absolute;
        right: 15px;
        top: calc(50% - var(--panel-toggle-offset, 2px));
      }
      &:not([open]) {
        --panel-toggle-angle: 135deg;
        --panel-toggle-offset: 4px;
      }
    }
    details[part='panel'] {
      background: var(--card-bg);
      border: var(--card-border);
      border-radius: 4px;
      user-select: none;

      &:has(summary:hover) {
        border-color: var(--accent-color);
      }

      & > summary,
      summary::part(header) {
        padding: 10px 10px;
        padding-right: 25px;
      }

      summary:hover,
      summary::part(header):hover {
        --panel-toggle-opacity: 0.5;
      }

      input[type='checkbox'],
      summary::part(checkbox) {
        margin: 0;
      }

      &:not([open]):hover {
        background: var(--card-hover-bg);
      }

      &[open] {
        background: var(--card-open-bg);
        box-shadow: var(--card-open-shadow);

        & > summary {
          font-weight: bold;
        }
      }
      .tabs {
        border-bottom: 1px solid var(--border-color);
        box-sizing: border-box;
        display: flex;
        height: 2.25rem;
      }

      .tab {
        background: none;
        border: none;
        border-bottom: 1px solid transparent;
        color: var(--color);
        font: var(--font-button);
        height: 2.25rem;
        padding: 0 0.75rem;
      }

      .tab[aria-selected='true'] {
        color: var(--color-high-contrast);
        border-bottom-color: var(--color-high-contrast);
      }

      .tab-content {
        flex: 1 1 auto;
        gap: var(--space-150);
        overflow: auto;
        padding: var(--space-150);
      }
    }
  `;
M([
  g()
], w.prototype, "javaPluginSectionOpened", 2);
M([
  g()
], w.prototype, "hotswapSectionOpened", 2);
M([
  g()
], w.prototype, "hotswapTab", 2);
M([
  g()
], w.prototype, "downloadStatusMessages", 2);
M([
  g()
], w.prototype, "downloadProgress", 2);
w = M([
  cl(w.NAME)
], w);
const Y = $c({
  header: "Development Workflow",
  tag: Pc,
  width: 800,
  height: 800,
  floatingPosition: {
    top: 50,
    left: 50
  },
  individual: true
}), Ue = {
  init(e) {
    e.addPanel(Y);
  }
};
window.Vaadin.copilot.plugins.push(Ue);
$.addPanel(Y);
var Ve = (e, t, o, i) => {
  for (var n = t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = r(n) || n);
  return n;
};
let ee = class extends Pl {
  constructor() {
    super(...arguments), this.clickListener = uu;
  }
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.classList.add("custom-menu-item"), this.addEventListener("click", this.clickListener);
  }
  disconnectedCallback() {
    super.disconnectedCallback(), this.removeEventListener("click", this.clickListener);
  }
  render() {
    const e = fu(), t = e.status === "warning" || e.status === "error";
    return ct`
      <div style="flex-grow: 1;">
        <div class="label">Development workflow</div>
        <div class="status ${t ? e.status : ""}">${e.message}</div>
      </div>
      ${t ? ct`<div class="${e.status} icon"></div>` : O}
    `;
  }
};
ee = Ve([
  cl("copilot-activation-button-development-workflow")
], ee);
var Xe = (e, t, o, i) => {
  for (var n = t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = r(n) || n);
  return n;
};
let te = class extends Pl {
  constructor() {
    super(), this.clickListener = this.getClickListener(), this.reaction(
      () => p$1.userInfo,
      () => {
        this.requestUpdate();
      }
    );
  }
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.classList.add("custom-menu-item"), this.addEventListener("click", this.clickListener);
  }
  disconnectedCallback() {
    super.disconnectedCallback(), this.removeEventListener("click", this.clickListener);
  }
  render() {
    const e = this.getStatus();
    return ct`
      <div style="flex-grow: 1;">
        <div class="label user">${this.getUsername()}</div>
        ${e ? ct`<div class="warning status">${e}</div>` : O}
      </div>
      ${this.renderPortrait()} ${this.renderDot()}
    `;
  }
  getClickListener() {
    var _a2;
    return ((_a2 = p$1.userInfo) == null ? void 0 : _a2.validLicense) ? () => window.open("https://vaadin.com/myaccount", "_blank", "noopener") : () => p$1.setLoginCheckActive(true);
  }
  getUsername() {
    var _a2;
    return ((_a2 = p$1.userInfo) == null ? void 0 : _a2.firstName) ? `${p$1.userInfo.firstName} ${p$1.userInfo.lastName}` : "Log in";
  }
  getStatus() {
    var _a2, _b2, _c;
    if (!((_a2 = p$1.userInfo) == null ? void 0 : _a2.validLicense)) {
      if (wo.active) {
        const e = Math.round(wo.remainingTimeInMillis / 864e5);
        return `Preview expires in ${e}${e === 1 ? " day" : " days"}`;
      }
      if (wo.expired && !((_b2 = p$1.userInfo) == null ? void 0 : _b2.validLicense))
        return "Preview expired";
      if (!wo.active && !wo.expired && !((_c = p$1.userInfo) == null ? void 0 : _c.validLicense))
        return "No valid license available";
    }
  }
  renderPortrait() {
    var _a2;
    return ((_a2 = p$1.userInfo) == null ? void 0 : _a2.portraitUrl) ? ct`<div
        class="portrait"
        style="background-image: url('https://vaadin.com${p$1.userInfo.portraitUrl}')"></div>` : O;
  }
  renderDot() {
    var _a2;
    return ((_a2 = p$1.userInfo) == null ? void 0 : _a2.validLicense) ? O : wo.active || wo.expired ? ct`<div class="icon warning"></div>` : O;
  }
};
te = Xe([
  cl("copilot-activation-button-user-info")
], te);
var Ye = Object.defineProperty, We = Object.getOwnPropertyDescriptor, B = (e, t, o, i) => {
  for (var n = i > 1 ? void 0 : i ? We(t, o) : t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = (i ? r(t, o, n) : r(n)) || n);
  return i && n && Ye(t, o, n), n;
};
const Ge = 8;
let _ = class extends Pl {
  constructor() {
    super(...arguments), this.initialMouseDownPosition = null, this.dragging = false, this.items = [], this.mouseDownListener = (e) => {
      this.initialMouseDownPosition = { x: e.clientX, y: e.clientY }, x.draggingStarts(this, e), document.addEventListener("mousemove", this.documentDraggingMouseMoveEventListener);
    }, this.documentDraggingMouseMoveEventListener = (e) => {
      if (this.initialMouseDownPosition && !this.dragging) {
        const { clientX: t, clientY: o } = e;
        this.dragging = Math.abs(t - this.initialMouseDownPosition.x) + Math.abs(o - this.initialMouseDownPosition.y) > Ge;
      }
      this.dragging && (this.setOverlayVisibility(false), x.dragging(this, e));
    }, this.documentMouseUpListener = (e) => {
      if (this.initialMouseDownPosition = null, document.removeEventListener("mousemove", this.documentDraggingMouseMoveEventListener), this.dragging) {
        const t = x.dragging(this, e);
        fn.setActivationButtonPosition(t), this.setOverlayVisibility(true);
      } else
        this.setMenuBarOnClick();
      this.dragging = false;
    }, this.closeMenuMouseMoveListener = (e) => {
      e.composedPath().some((i) => {
        if (i instanceof HTMLElement) {
          const n = i;
          if (n.localName === this.localName || n.localName === "vaadin-menu-bar-overlay" && n.classList.contains("activation-button-menu"))
            return true;
        }
        return this.checkPointerIsInRangeInSurroundingRectangle(e);
      }) || this.closeMenu();
    }, this.checkPointerIsInRangeInSurroundingRectangle = (e) => {
      var _a2, _b2;
      const o = (_b2 = (_a2 = document.querySelector("copilot-main")) == null ? void 0 : _a2.shadowRoot) == null ? void 0 : _b2.querySelector("vaadin-menu-bar-overlay.activation-button-menu"), i = this.menubar;
      if (!o)
        return false;
      const n = o.querySelector("vaadin-menu-bar-list-box");
      if (!n)
        return false;
      const a = n.getBoundingClientRect(), r = i.getBoundingClientRect(), h2 = Math.min(a.x, r.x), g2 = Math.min(a.y, r.y), v2 = Math.max(a.width, r.width), y = a.height + r.height;
      return Gc(new DOMRect(h2, g2, v2, y), e.clientX, e.clientY);
    }, this.dispatchSpotlightActivationEvent = (e) => {
      this.dispatchEvent(
        new CustomEvent("spotlight-activation-changed", {
          detail: e
        })
      );
    }, this.activationBtnClicked = (e) => {
      if (p$1.active && this.handleAttentionRequiredOnClick()) {
        e == null ? void 0 : e.stopPropagation(), e == null ? void 0 : e.preventDefault();
        return;
      }
      e == null ? void 0 : e.stopPropagation(), this.dispatchEvent(new CustomEvent("activation-btn-clicked"));
    }, this.handleAttentionRequiredOnClick = () => {
      const e = $.getAttentionRequiredPanelConfiguration();
      return e ? e.panel && !e.floating ? (A.emit("open-attention-required-drawer", null), true) : ($.clearAttention(), true) : false;
    }, this.closeMenu = () => {
      this.menubar._close(), document.removeEventListener("mousemove", this.closeMenuMouseMoveListener);
    }, this.setMenuBarOnClick = () => {
      const e = this.shadowRoot.querySelector("vaadin-menu-bar-button");
      e && (e.onclick = this.activationBtnClicked);
    };
  }
  static get styles() {
    return [
      le(X),
      ul`
        :host {
          --space: 8px;
          --height: 28px;
          --width: 28px;
          position: absolute;
          top: clamp(var(--space), var(--top), calc(100vh - var(--height) - var(--space)));
          left: clamp(var(--space), var(--left), calc(100vw - var(--width) - var(--space)));
          bottom: clamp(var(--space), var(--bottom), calc(100vh - var(--height) - var(--space)));
          right: clamp(var(--space), var(--right), calc(100vw - var(--width) - var(--space)));
          user-select: none;
          -ms-user-select: none;
          -moz-user-select: none;
          -webkit-user-select: none;
          --indicator-color: var(--red);
          /* Don't add a z-index or anything else that creates a stacking context */
        }

        :host .menu-button {
          min-width: unset;
        }

        :host([document-hidden]) {
          -webkit-filter: grayscale(100%); /* Chrome, Safari, Opera */
          filter: grayscale(100%);
        }

        .menu-button::part(container) {
          overflow: visible;
        }

        .menu-button vaadin-menu-bar-button {
          all: initial;
          display: block;
          position: relative;
          z-index: var(--z-index-activation-button);
          width: var(--width);
          height: var(--height);
          overflow: hidden;
          color: transparent;
          background: hsl(0 0% 0% / 0.25);
          border-radius: 8px;
          box-shadow: 0 0 0 1px hsl(0 0% 100% / 0.1);
          cursor: default;
          -webkit-backdrop-filter: blur(8px);
          backdrop-filter: blur(8px);
          transition:
            box-shadow 0.2s,
            background-color 0.2s;
        }

        /* visual effect when active */

        .menu-button vaadin-menu-bar-button::before {
          all: initial;
          content: '';
          background-image: radial-gradient(circle at 50% -10%, hsl(221 100% 55% / 0.6) 0%, transparent 60%),
            radial-gradient(circle at 25% 40%, hsl(303 71% 64%) 0%, transparent 70%),
            radial-gradient(circle at 80% 10%, hsla(262, 38%, 9%, 0.5) 0%, transparent 80%),
            radial-gradient(circle at 110% 50%, hsla(147, 100%, 77%, 1) 20%, transparent 100%);
          animation: 5s swirl linear infinite;
          animation-play-state: paused;
          inset: -6px;
          opacity: 0;
          position: absolute;
          transition: opacity 0.5s;
        }

        /* vaadin symbol */

        .menu-button vaadin-menu-bar-button::after {
          all: initial;
          content: '';
          position: absolute;
          inset: 1px;
          background: url('data:image/svg+xml;utf8,<svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M12.7407 9.70401C12.7407 9.74417 12.7378 9.77811 12.7335 9.81479C12.7111 10.207 12.3897 10.5195 11.9955 10.5195C11.6014 10.5195 11.2801 10.209 11.2577 9.8169C11.2534 9.7801 11.2504 9.74417 11.2504 9.70401C11.2504 9.31225 11.1572 8.90867 10.2102 8.90867H7.04307C5.61481 8.90867 5 8.22698 5 6.86345V5.70358C5 5.31505 5.29521 5 5.68008 5C6.06495 5 6.35683 5.31505 6.35683 5.70358V6.09547C6.35683 6.53423 6.655 6.85413 7.307 6.85413H10.4119C11.8248 6.85413 11.9334 7.91255 11.98 8.4729H12.0111C12.0577 7.91255 12.1663 6.85413 13.5791 6.85413H16.6841C17.3361 6.85413 17.614 6.54529 17.614 6.10641L17.6158 5.70358C17.6158 5.31505 17.9246 5 18.3095 5C18.6943 5 19 5.31505 19 5.70358V6.86345C19 8.22698 18.3763 8.90867 16.9481 8.90867H13.7809C12.8338 8.90867 12.7407 9.31225 12.7407 9.70401Z" fill="white"/><path d="M12.7536 17.7785C12.6267 18.0629 12.3469 18.2608 12.0211 18.2608C11.6907 18.2608 11.4072 18.0575 11.2831 17.7668C11.2817 17.7643 11.2803 17.7619 11.279 17.7595C11.2761 17.7544 11.2732 17.7495 11.2704 17.744L8.45986 12.4362C8.3821 12.2973 8.34106 12.1399 8.34106 11.9807C8.34106 11.4732 8.74546 11.0603 9.24238 11.0603C9.64162 11.0603 9.91294 11.2597 10.0985 11.6922L12.0216 15.3527L13.9468 11.6878C14.1301 11.2597 14.4014 11.0603 14.8008 11.0603C15.2978 11.0603 15.7021 11.4732 15.7021 11.9807C15.7021 12.1399 15.6611 12.2973 15.5826 12.4374L12.7724 17.7446C12.7683 17.7524 12.7642 17.7597 12.7601 17.767C12.7579 17.7708 12.7557 17.7746 12.7536 17.7785Z" fill="white"/></svg>');
          background-size: 100%;
        }

        .menu-button vaadin-menu-bar-button[focus-ring] {
          outline: 2px solid var(--selection-color);
          outline-offset: 2px;
        }

        .menu-button vaadin-menu-bar-button:hover {
          background: hsl(0 0% 0% / 0.8);
          box-shadow:
            0 0 0 1px hsl(0 0% 100% / 0.1),
            0 2px 8px -1px hsl(0 0% 0% / 0.3);
        }

        :host([active]) .menu-button vaadin-menu-bar-button {
          background-color: transparent;
          box-shadow:
            inset 0 0 0 1px hsl(0 0% 0% / 0.2),
            0 2px 8px -1px hsl(0 0% 0% / 0.3);
        }

        :host([active]) .menu-button vaadin-menu-bar-button::before {
          opacity: 1;
          animation-play-state: running;
        }

        :host([attention-required]) {
          animation: bounce 0.5s;
          animation-iteration-count: 2;
        }

        [part='indicator'] {
          top: -6px;
          right: -6px;
          width: 12px;
          height: 12px;
          box-sizing: border-box;
          border-radius: 100%;
          position: absolute;
          display: var(--indicator-display, none);
          background: var(--indicator-color);
          z-index: calc(var(--z-index-activation-button) + 1);
          border: 2px solid var(--indicator-border);
        }

        :host([indicator='warning']) {
          --indicator-display: block;
          --indicator-color: var(--yellow);
        }

        :host([indicator='error']) {
          --indicator-display: block;
          --indicator-color: var(--red);
        }
      `
    ];
  }
  connectedCallback() {
    super.connectedCallback(), this.reaction(
      () => $.attentionRequiredPanelTag,
      () => {
        this.toggleAttribute(Uc, $.attentionRequiredPanelTag !== null), this.updateIndicator();
      }
    ), this.reaction(
      () => p$1.active,
      () => {
        this.toggleAttribute("active", p$1.active);
      },
      { fireImmediately: true }
    ), this.addEventListener("mousedown", this.mouseDownListener), document.addEventListener("mouseup", this.documentMouseUpListener);
    const e = fn.getActivationButtonPosition();
    e ? (this.style.setProperty("--left", `${e.left}px`), this.style.setProperty("--bottom", `${e.bottom}px`), this.style.setProperty("--right", `${e.right}px`), this.style.setProperty("--top", `${e.top}px`)) : (this.style.setProperty("--bottom", "var(--space)"), this.style.setProperty("--right", "var(--space)")), A.on("document-activation-change", (t) => {
      this.toggleAttribute("document-hidden", !t.detail.active);
    }), this.reaction(
      () => [p$1.jdkInfo, p$1.idePluginState],
      () => {
        this.updateIndicator();
      }
    ), this.reaction(
      () => [p$1.active, p$1.idePluginState, fn.isActivationAnimation()],
      () => {
        this.generateItems();
      }
    );
  }
  disconnectedCallback() {
    super.disconnectedCallback(), this.removeEventListener("mousedown", this.mouseDownListener), document.removeEventListener("mouseup", this.documentMouseUpListener);
  }
  updateIndicator() {
    if (this.hasAttribute(Uc)) {
      this.setAttribute("indicator", "error");
      return;
    }
    const e = fu();
    e.status !== "success" ? this.setAttribute("indicator", e.status) : this.removeAttribute("indicator");
  }
  /**
   * To hide overlay while dragging
   * @param visible
   */
  setOverlayVisibility(e) {
    const t = this.shadowRoot.querySelector("vaadin-menu-bar-button").__overlay;
    e ? (t == null ? void 0 : t.style.setProperty("display", "flex"), t == null ? void 0 : t.style.setProperty("visibility", "visible")) : (t == null ? void 0 : t.style.setProperty("display", "none"), t == null ? void 0 : t.style.setProperty("visibility", "invisible"));
  }
  generateItems() {
    var _a2, _b2, _c;
    const e = [
      {
        text: "Vaadin Copilot",
        children: []
      }
    ];
    ((_a2 = p$1.userInfo) == null ? void 0 : _a2.copilotProjectCannotLeaveLocalhost) !== true && e[0].children.push({
      component: f("vaadin-menu-bar-item", {
        label: "Toggle Command Window",
        hint: iu.toggleCommandWindow,
        style: "toggle-spotlight"
      }),
      action: "spotlight"
    }), p$1.active && (((_c = (_b2 = p$1.idePluginState) == null ? void 0 : _b2.supportedActions) == null ? void 0 : _c.find((t) => t === "undo")) && (e[0].children = [
      {
        component: f("vaadin-menu-bar-item", {
          label: "Undo",
          hint: iu.undo
        }),
        action: "undo"
      },
      {
        component: f("vaadin-menu-bar-item", {
          label: "Redo",
          hint: iu.redo
        }),
        action: "redo"
      },
      ...e[0].children
    ]), e[0].children = [
      {
        component: f("vaadin-menu-bar-item", {
          label: "Tell us what you think"
          // Label used also in ScreenshotsIT.java
        }),
        action: "feedback"
      },
      {
        component: f("vaadin-menu-bar-item", {
          label: "Show welcome message"
        }),
        action: "welcome"
      },
      {
        component: f("vaadin-menu-bar-item", {
          label: "Show keyboard shortcuts"
        }),
        action: "shortcuts"
      },
      {
        text: "Settings",
        children: [
          {
            component: f("vaadin-menu-bar-item", {
              label: "Activation shortcut enabled",
              hint: fn.isActivationShortcut() ? "✓" : void 0
            }),
            action: "shortcut"
          },
          {
            component: f("vaadin-menu-bar-item", {
              label: "Show animation when activating",
              hint: fn.isActivationAnimation() ? "✓" : void 0
            }),
            action: "animate-on-activate"
          }
        ]
      },
      {
        component: "hr"
      },
      ...e[0].children
    ]), e[0].children = [
      ...e[0].children,
      { component: "hr" },
      {
        component: f("vaadin-menu-bar-item", {
          label: '<span class="deactivate">Deactivate</span><span class="activate">Activate</span> Copilot',
          hint: fn.isActivationShortcut() ? iu.toggleCopilot : void 0
        }),
        action: "copilot"
      }
    ], e[0].children.unshift({ component: "copilot-activation-button-development-workflow" }), p$1.active && (e[0].children.unshift({ component: "hr" }), e[0].children.unshift({ component: "copilot-activation-button-user-info" })), this.items = e;
  }
  render() {
    return ct`
      <vaadin-menu-bar
        class="menu-button"
        .items="${this.items}"
        @item-selected="${(e) => {
      this.handleMenuItemClick(e.detail.value);
    }}"
        ?open-on-hover=${!this.dragging}
        @mouseenter="${() => {
      document.addEventListener("mousemove", this.closeMenuMouseMoveListener);
    }}"
        overlay-class="activation-button-menu">
      </vaadin-menu-bar>
      <div part="indicator"></div>
    `;
  }
  handleMenuItemClick(e) {
    if (this.closeMenu(), e.onClick) {
      e.onClick();
      return;
    }
    switch (e.action) {
      case "copilot":
        this.activationBtnClicked();
        break;
      case "spotlight":
        p$1.setSpotlightActive(!p$1.spotlightActive);
        break;
      case "shortcut":
        fn.setActivationShortcut(!fn.isActivationShortcut());
        break;
      case "animate-on-activate":
        fn.setActivationAnimation(!fn.isActivationAnimation());
        break;
      case "undo":
      case "redo":
        A.emit("undoRedo", { undo: e.action === "undo" });
        break;
      case "feedback":
        $.updatePanel("copilot-feedback-panel", {
          floating: true
        });
        break;
      case "welcome":
        p$1.setWelcomeActive(true), p$1.setSpotlightActive(true);
        break;
      case "shortcuts":
        $.updatePanel("copilot-shortcuts-panel", {
          floating: true
        });
        break;
    }
  }
  firstUpdated() {
    this.setMenuBarOnClick(), u(this.shadowRoot);
  }
};
B([
  v("vaadin-menu-bar")
], _.prototype, "menubar", 2);
B([
  g()
], _.prototype, "dragging", 2);
B([
  g()
], _.prototype, "items", 2);
_ = B([
  cl("copilot-activation-button")
], _);
var Ke = Object.defineProperty, Ze = Object.getOwnPropertyDescriptor, L = (e, t, o, i) => {
  for (var n = i > 1 ? void 0 : i ? Ze(t, o) : t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = (i ? r(t, o, n) : r(n)) || n);
  return i && n && Ke(t, o, n), n;
};
const m = "resize-dir", V = "floating-resizing-active";
let z = class extends Pl {
  constructor() {
    super(...arguments), this.panelTag = "", this.dockingItems = [
      {
        component: f("vaadin-context-menu-item", {
          icon: r$1.dockRight,
          label: "Dock right"
        }),
        panel: "right"
      },
      {
        component: f("vaadin-context-menu-item", {
          icon: r$1.dockLeft,
          label: "Dock left"
        }),
        panel: "left"
      },
      {
        component: f("vaadin-context-menu-item", {
          icon: r$1.dockBottom,
          label: "Dock bottom"
        }),
        panel: "bottom"
      }
    ], this.floatingResizingStarted = false, this.resizingInDrawerStarted = false, this.toggling = false, this.rectangleBeforeResizing = null, this.floatingResizeHandlerMouseMoveListener = (e) => {
      var _a2, _b2;
      if (!((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) || this.floatingResizingStarted || !((_b2 = this.panelInfo) == null ? void 0 : _b2.expanded))
        return;
      const t = this.getBoundingClientRect(), o = Math.abs(e.clientX - t.x), i = Math.abs(t.x + t.width - e.clientX), n = Math.abs(e.clientY - t.y), a = Math.abs(t.y + t.height - e.clientY), r = Number.parseInt(
        window.getComputedStyle(this).getPropertyValue("--floating-offset-resize-threshold"),
        10
      );
      let h2 = "";
      o < r ? n < r ? (h2 = "nw-resize", this.setAttribute(m, "top left")) : a < r ? (h2 = "sw-resize", this.setAttribute(m, "bottom left")) : (h2 = "col-resize", this.setAttribute(m, "left")) : i < r ? n < r ? (h2 = "ne-resize", this.setAttribute(m, "top right")) : a < r ? (h2 = "se-resize", this.setAttribute(m, "bottom right")) : (h2 = "col-resize", this.setAttribute(m, "right")) : a < r ? (h2 = "row-resize", this.setAttribute(m, "bottom")) : n < r && (h2 = "row-resize", this.setAttribute(m, "top")), h2 !== "" ? (this.rectangleBeforeResizing = this.getBoundingClientRect(), this.style.setProperty("--resize-cursor", h2)) : (this.style.removeProperty("--resize-cursor"), this.removeAttribute(m)), this.toggleAttribute(V, h2 !== "");
    }, this.floatingResizingMouseDownListener = (e) => {
      if (!this.hasAttribute(V) || e.button !== 0)
        return;
      e.stopPropagation(), e.preventDefault(), x.anchorLeftTop(this), this.floatingResizingStarted = true, this.toggleAttribute("resizing", true);
      const t = this.getResizeDirections(), { clientX: o, clientY: i } = e;
      (t.includes("top") || t.includes("bottom")) && this.style.setProperty("--section-height", null), t.forEach((n) => this.setResizePosition(n, o, i)), p$1.setSectionPanelResizing(true);
    }, this.floatingResizingMouseLeaveListener = () => {
      var _a2;
      ((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) && (this.floatingResizingStarted || (this.removeAttribute("resizing"), this.removeAttribute(V), this.removeAttribute("dragging"), this.style.removeProperty("--resize-cursor"), this.removeAttribute(m)));
    }, this.floatingResizingMouseMoveListener = (e) => {
      var _a2;
      if (!((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) || !this.floatingResizingStarted)
        return;
      e.stopPropagation(), e.preventDefault();
      const t = this.getResizeDirections(), { clientX: o, clientY: i } = e;
      t.forEach((n) => this.setResizePosition(n, o, i));
    }, this.setFloatingResizeDirectionProps = (e, t, o, i) => {
      o && o > Number.parseFloat(window.getComputedStyle(this).getPropertyValue("--min-width")) && (this.style.setProperty(`--${e}`, `${t}px`), this.style.setProperty("width", `${o}px`));
      const n = window.getComputedStyle(this), a = Number.parseFloat(n.getPropertyValue("--header-height")), r = Number.parseFloat(n.getPropertyValue("--floating-offset-resize-threshold")) / 2;
      i && i > a + r && (this.style.setProperty(`--${e}`, `${t}px`), this.style.setProperty("height", `${i}px`), this.container.style.setProperty("margin-top", "calc(var(--floating-offset-resize-threshold) / 4)"), this.container.style.height = `calc(${i}px - var(--floating-offset-resize-threshold) / 2)`);
    }, this.floatingResizingMouseUpListener = (e) => {
      var _a2;
      if (!this.floatingResizingStarted || !((_a2 = this.panelInfo) == null ? void 0 : _a2.floating))
        return;
      e.stopPropagation(), e.preventDefault(), this.floatingResizingStarted = false, p$1.setSectionPanelResizing(false);
      const { width: t, height: o } = this.getBoundingClientRect(), { left: i, top: n, bottom: a, right: r } = x.anchor(this), h2 = window.getComputedStyle(this.container), g2 = Number.parseInt(h2.borderTopWidth, 10), v2 = Number.parseInt(h2.borderTopWidth, 10);
      $.updatePanel(this.panelInfo.tag, {
        width: t,
        height: o - (g2 + v2),
        floatingPosition: {
          ...this.panelInfo.floatingPosition,
          left: i,
          top: n,
          bottom: a,
          right: r
        }
      }), this.style.removeProperty("width"), this.style.removeProperty("height"), this.container.style.removeProperty("height"), this.container.style.removeProperty("margin-top"), this.setCssSizePositionProperties(), this.toggleAttribute("dragging", false);
    }, this.transitionEndEventListener = () => {
      this.toggling && (this.toggling = false, x.anchor(this));
    }, this.resizeInDrawerMouseDownListener = (e) => {
      e.button === 0 && (this.resizingInDrawerStarted = true, this.setAttribute("resizing", ""), A.emit("user-select", { allowSelection: false }));
    }, this.resizeInDrawerMouseMoveListener = (e) => {
      if (!this.resizingInDrawerStarted)
        return;
      const { y: t } = e;
      e.stopPropagation(), e.preventDefault();
      const o = t - this.getBoundingClientRect().top;
      this.style.setProperty("--section-height", `${o}px`), $.updatePanel(this.panelInfo.tag, {
        height: o
      });
    }, this.resizeInDrawerMouseUpListener = () => {
      var _a2;
      this.resizingInDrawerStarted && (((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) || (this.resizingInDrawerStarted = false, this.removeAttribute("resizing"), A.emit("user-select", { allowSelection: true }), this.style.setProperty("--section-height", `${this.getBoundingClientRect().height}px`)));
    }, this.sectionPanelMouseEnterListener = () => {
      this.hasAttribute(Uc) && (this.removeAttribute(Uc), $.clearAttention());
    }, this.contentAreaMouseDownListener = () => {
      $.bringToFront(this.panelInfo.tag);
    }, this.documentMouseUpEventListener = () => {
      var _a2;
      document.removeEventListener("mousemove", this.draggingEventListener), ((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) && (this.toggleAttribute("dragging", false), p$1.setSectionPanelDragging(false));
    }, this.panelHeaderMouseDownEventListener = (e) => {
      e.button === 0 && ($.bringToFront(this.panelInfo.tag), !this.hasAttribute(m) && (e.target instanceof HTMLButtonElement && e.target.getAttribute("part") === "title-button" ? this.startDraggingDebounce(e) : this.startDragging(e)));
    }, this.panelHeaderMouseUpEventListener = (e) => {
      e.button === 0 && this.startDraggingDebounce.clear();
    }, this.startDragging = (e) => {
      var _a2;
      x.draggingStarts(this, e), document.addEventListener("mousemove", this.draggingEventListener), p$1.setSectionPanelDragging(true), ((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) ? this.toggleAttribute("dragging", true) : this.parentElement.sectionPanelDraggingStarted(this, e), e.preventDefault(), e.stopPropagation();
    }, this.startDraggingDebounce = xc(this.startDragging, 200), this.draggingEventListener = (e) => {
      var _a2, _b2;
      const t = x.dragging(this, e);
      if (((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) && ((_b2 = this.panelInfo) == null ? void 0 : _b2.floatingPosition)) {
        e.preventDefault();
        const { left: o, top: i, bottom: n, right: a } = t;
        $.updatePanel(this.panelInfo.tag, {
          floatingPosition: {
            ...this.panelInfo.floatingPosition,
            left: o,
            top: i,
            bottom: n,
            right: a
          }
        });
      }
    }, this.setCssSizePositionProperties = () => {
      var _a2;
      const e = $.getPanelByTag(this.panelTag);
      if (e && (e.height !== void 0 && (((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) || e.panel === "left" || e.panel === "right" ? this.style.setProperty("--section-height", `${e.height}px`) : this.style.removeProperty("--section-height")), e.width !== void 0 && (e.floating || e.panel === "bottom" ? this.style.setProperty("--section-width", `${e.width}px`) : this.style.removeProperty("--section-width")), e.floating && e.floatingPosition && !this.toggling)) {
        const { left: t, top: o, bottom: i, right: n } = e.floatingPosition;
        this.style.setProperty("--left", t !== void 0 ? `${t}px` : "auto"), this.style.setProperty("--top", o !== void 0 ? `${o}px` : "auto"), this.style.setProperty("--bottom", i !== void 0 ? `${i}px` : ""), this.style.setProperty("--right", n !== void 0 ? `${n}px` : "");
      }
    }, this.renderPopupButton = () => {
      if (!this.panelInfo)
        return O;
      let e;
      return this.panelInfo.panel === void 0 ? e = "Close the popup" : e = this.panelInfo.floating ? `Dock ${this.panelInfo.header} to ${this.panelInfo.panel}` : `Open ${this.panelInfo.header} as a popup`, ct`
      <vaadin-context-menu .items=${this.dockingItems} @item-selected="${this.changeDockingPanel}">
        <button
          part="popup-button"
          @click="${(t) => this.changePanelFloating(t)}"
          @mousedown="${(t) => t.stopPropagation()}"
          title="${e}"
          aria-label=${e}>
          ${this.getPopupButtonIcon()}
        </button>
      </vaadin-context-menu>
    `;
    }, this.changePanelFloating = (e) => {
      var _a2, _b2, _c;
      if (this.panelInfo)
        if (e.stopPropagation(), ou(this), (_a2 = this.panelInfo) == null ? void 0 : _a2.floating)
          $.updatePanel(this.panelInfo.tag, { floating: false });
        else {
          let t;
          if (this.panelInfo.floatingPosition)
            t = this.panelInfo.floatingPosition;
          else {
            const { left: n, top: a } = this.getBoundingClientRect();
            t = {
              left: n,
              top: a
            };
          }
          let o = (_b2 = this.panelInfo) == null ? void 0 : _b2.height;
          o === void 0 && this.panelInfo.expanded && (o = Number.parseInt(window.getComputedStyle(this).height, 10)), this.parentElement.forceClose(), $.updatePanel(this.panelInfo.tag, {
            floating: true,
            expanded: true,
            width: ((_c = this.panelInfo) == null ? void 0 : _c.width) || Number.parseInt(window.getComputedStyle(this).width, 10),
            height: o,
            floatingPosition: t
          }), $.bringToFront(this.panelInfo.tag);
        }
    }, this.toggleExpand = (e) => {
      this.panelInfo && (e.stopPropagation(), x.anchorLeftTop(this), $.updatePanel(this.panelInfo.tag, {
        expanded: !this.panelInfo.expanded
      }), this.toggling = true, this.toggleAttribute("expanded", this.panelInfo.expanded));
    };
  }
  static get styles() {
    return [
      le(X),
      ul`
        * {
          box-sizing: border-box;
        }

        :host {
          flex: none;
          display: grid;
          align-content: start;
          grid-template-rows: auto 1fr;
          transition: grid-template-rows var(--duration-2);
          overflow: hidden;
          position: relative;
          --min-width: 160px;
          --resize-div-size: 10px;
          --header-height: 37px;
          --content-height: calc(var(--section-height) - var(--header-height));
          --content-width: var(--content-width, 100%);
          --floating-border-width: 1px;
          --floating-offset-resize-threshold: 8px;
          cursor: var(--cursor, var(--resize-cursor, default));
        }

        :host(:not([expanded])) {
          grid-template-rows: auto 0fr;
          --content-height: 0px !important;
        }

        [part='header'] {
          align-items: center;
          color: var(--color-high-contrast);
          display: flex;
          flex: none;
          font: var(--font-small-medium);
          justify-content: space-between;
          min-width: 100%;
          user-select: none;
          -webkit-user-select: none;
          width: var(--min-width);
          height: var(--header-height);
        }

        :host([floating]:not([expanded])) [part='header'] {
          --min-width: unset;
        }

        [part='header'] {
          border-bottom: 1px solid var(--border-color);
        }

        :host([floating]) [part='header'] {
          transition: border-color var(--duration-2);
        }

        :host([floating]:not([expanded])) [part='header'] {
          border-color: transparent;
        }

        [part='title'] {
          flex: auto;
          margin: 0;
          overflow: hidden;
          text-overflow: ellipsis;
        }

        [part='content'] {
          height: var(--content-height);
          overflow: auto;
          transition:
            height var(--duration-2),
            width var(--duration-2),
            opacity var(--duration-2),
            visibility calc(var(--duration-2) * 2);
        }

        [part='drawer-resize'] {
          resize: vertical;
          cursor: row-resize;
          position: absolute;
          bottom: -5px;
          left: 0;
          width: 100%;
          height: 10px;
        }

        :host([floating]) [part='drawer-resize'] {
          display: none;
        }

        :host(:not([expanded])) [part='drawer-resize'] {
          display: none;
        }

        :host(:not([floating]):not(:last-child)) {
          border-bottom: 1px solid var(--border-color);
        }

        :host(:not([expanded])) [part='content'] {
          opacity: 0;
          visibility: hidden;
        }

        :host([floating]:not([expanded])) [part='content'] {
          width: 0;
          height: 0;
        }

        :host(:not([expanded])) [part='content'][style*='height'] {
          height: 0 !important;
        }

        :host(:not([expanded])) [part='content'][style*='width'] {
          width: 0 !important;
        }

        :host([floating]) {
          position: fixed;
          min-width: 0;
          min-height: 0;
          z-index: calc(var(--z-index-floating-panel) + var(--z-index-focus, 0));
          top: clamp(0px, var(--top), calc(100vh - var(--section-height, var(--header-height)) * 0.5));
          left: clamp(calc(var(--section-width) * -0.5), var(--left), calc(100vw - var(--section-width) * 0.5));
          bottom: clamp(
            calc(var(--section-height, var(--header-height)) * -0.5),
            var(--bottom),
            calc(100vh - var(--section-height, var(--header-height)) * 0.5)
          );
          right: clamp(calc(var(--section-width) * -0.5), var(--right), calc(100vw - var(--section-width) * 0.5));
          width: var(--section-width);
          overflow: visible;
        }
        :host([floating]) [part='container'] {
          background: var(--surface);
          border: var(--floating-border-width) solid var(--surface-border-color);
          -webkit-backdrop-filter: var(--surface-backdrop-filter);
          backdrop-filter: var(--surface-backdrop-filter);
          border-radius: var(--radius-2);
          margin: auto;
          box-shadow: var(--surface-box-shadow-2);
        }
        [part='container'] {
          overflow: hidden;
        }
        :host([floating][expanded]) {
          max-height: 100vh;
        }
        :host([floating][expanded]) [part='container'] {
          height: calc(100% - var(--floating-offset-resize-threshold) / 2);
          width: calc(100% - var(--floating-offset-resize-threshold) / 2);
        }

        :host([floating]:not([expanded])) {
          width: unset;
        }

        :host([floating]) .drag-handle {
          cursor: var(--resize-cursor, move);
        }

        :host([floating][expanded]) [part='content'] {
          min-width: var(--min-width);
          min-height: 0;
          width: var(--content-width);
        }

        /* :hover for Firefox, :active for others */

        :host([floating][expanded]) [part='content']:is(:hover, :active) {
          transition: none;
        }

        [part='header'] button {
          align-items: center;
          appearance: none;
          background: transparent;
          border: 0px;
          border-radius: var(--radius-1);
          color: var(--color);
          display: flex;
          flex: 0 0 auto;
          height: 2.25rem;
          justify-content: center;
          padding: 0px;
          width: 16px;
          margin-left: 10px;
          margin-right: 10px;
        }

        div.actions {
          width: auto;
        }

        :host(:not([expanded])) div.actions {
          display: none;
        }

        [part='title'] button {
          color: var(--color-high-contrast);
          font: var(--font-xsmall-semibold);
          width: auto;
        }

        [part='header'] button:hover {
          color: var(--color-high-contrast);
        }

        [part='header'] button:focus-visible {
          outline: 2px solid var(--blue-500);
          outline-offset: -2px;
        }

        [part='header'] button svg {
          display: block;
        }

        [part='header'] .actions:empty {
          display: none;
        }

        ::slotted(*) {
          box-sizing: border-box;
          display: block;
          height: var(--content-height, var(--default-content-height, 100%));
          /* padding: var(--space-150); */
          width: 100%;
        }

        :host(:not([floating])) ::slotted(*) {
          /* padding-top: var(--space-50); */
        }
        /*workaround for outline to have a explicit height while floating by default. 
          may be removed after https://github.com/vaadin/web-components/issues/7620 is solved
        */
        :host([floating][expanded][paneltag='copilot-outline-panel']) {
          --grid-default-height: 400px;
        }

        :host([dragging]) {
          opacity: 0.4;
        }

        :host([dragging]) [part='content'] {
          pointer-events: none;
        }

        :host([resizing]),
        :host([resizing]) [part='content'] {
          transition: none;
        }
        :host([resizing]) [part='content'] {
          height: 100%;
        }

        :host([hiding-while-drag-and-drop]) {
          display: none;
        }

        // dragging in drawer

        :host(:not([floating])) .drag-handle {
          cursor: grab;
        }

        :host(:not([floating])[dragging]) .drag-handle {
          cursor: grabbing;
        }
      `
    ];
  }
  connectedCallback() {
    super.connectedCallback(), this.setAttribute("role", "region"), this.reaction(
      () => $.getAttentionRequiredPanelConfiguration(),
      () => {
        const e = $.getAttentionRequiredPanelConfiguration();
        this.toggleAttribute(Uc, (e == null ? void 0 : e.tag) === this.panelTag && (e == null ? void 0 : e.floating));
      }
    ), this.addEventListener("mouseenter", this.sectionPanelMouseEnterListener), document.addEventListener("mousemove", this.resizeInDrawerMouseMoveListener), document.addEventListener("mouseup", this.resizeInDrawerMouseUpListener), this.reaction(
      () => p$1.operationInProgress,
      () => {
        requestAnimationFrame(() => {
          var _a2;
          this.toggleAttribute(
            "hiding-while-drag-and-drop",
            p$1.operationInProgress === eu.DragAndDrop && ((_a2 = this.panelInfo) == null ? void 0 : _a2.floating) && !this.panelInfo.showWhileDragging
          );
        });
      }
    ), this.reaction(
      () => $.floatingPanelsZIndexOrder,
      () => {
        this.style.setProperty("--z-index-focus", `${$.getFloatingPanelZIndex(this.panelTag)}`);
      },
      { fireImmediately: true }
    ), this.addEventListener("transitionend", this.transitionEndEventListener), this.addEventListener("mousemove", this.floatingResizeHandlerMouseMoveListener), this.addEventListener("mousedown", this.floatingResizingMouseDownListener), this.addEventListener("mouseleave", this.floatingResizingMouseLeaveListener), document.addEventListener("mousemove", this.floatingResizingMouseMoveListener), document.addEventListener("mouseup", this.floatingResizingMouseUpListener);
  }
  disconnectedCallback() {
    super.disconnectedCallback(), this.removeEventListener("mouseenter", this.sectionPanelMouseEnterListener), this.drawerResizeElement.removeEventListener("mousedown", this.resizeInDrawerMouseDownListener), document.removeEventListener("mousemove", this.resizeInDrawerMouseMoveListener), document.removeEventListener("mouseup", this.resizeInDrawerMouseUpListener), this.removeEventListener("mousemove", this.floatingResizeHandlerMouseMoveListener), this.removeEventListener("mousedown", this.floatingResizingMouseDownListener), document.removeEventListener("mousemove", this.floatingResizingMouseMoveListener), document.removeEventListener("mouseup", this.floatingResizingMouseUpListener);
  }
  setResizePosition(e, t, o) {
    const i = this.rectangleBeforeResizing, n = 0, a = window.innerWidth, r = 0, h2 = window.innerHeight, g2 = Math.max(n, Math.min(a, t)), v2 = Math.max(r, Math.min(h2, o));
    if (e === "left")
      this.setFloatingResizeDirectionProps(
        "left",
        g2,
        i.left - g2 + i.width
      );
    else if (e === "right")
      this.setFloatingResizeDirectionProps(
        "right",
        g2,
        g2 - i.right + i.width
      );
    else if (e === "top") {
      const y = i.top - v2 + i.height;
      this.setFloatingResizeDirectionProps("top", v2, void 0, y);
    } else if (e === "bottom") {
      const y = v2 - i.bottom + i.height;
      this.setFloatingResizeDirectionProps("bottom", v2, void 0, y);
    }
  }
  willUpdate(e) {
    var _a2;
    super.willUpdate(e), e.has("panelTag") && (this.panelInfo = $.getPanelByTag(this.panelTag), this.setAttribute("aria-labelledby", this.panelInfo.tag.concat("-title"))), this.toggleAttribute("floating", (_a2 = this.panelInfo) == null ? void 0 : _a2.floating);
  }
  updated(e) {
    super.updated(e), this.setCssSizePositionProperties();
  }
  firstUpdated(e) {
    var _a2, _b2;
    super.firstUpdated(e), document.addEventListener("mouseup", this.documentMouseUpEventListener), this.headerDraggableArea.addEventListener("mousedown", this.panelHeaderMouseDownEventListener), this.headerDraggableArea.addEventListener("mouseup", this.panelHeaderMouseUpEventListener), this.toggleAttribute("expanded", (_a2 = this.panelInfo) == null ? void 0 : _a2.expanded), this.toggleAttribute("individual", ((_b2 = this.panelInfo) == null ? void 0 : _b2.individual) ?? false), au(this), this.setCssSizePositionProperties(), this.contentArea.addEventListener("mousedown", this.contentAreaMouseDownListener), this.drawerResizeElement.addEventListener("mousedown", this.resizeInDrawerMouseDownListener), u(this.shadowRoot);
  }
  render() {
    return this.panelInfo ? ct`
      <div part="container">
        <div part="header" class="drag-handle">
          ${this.panelInfo.expandable !== false ? ct` <button
                part="toggle-button"
                @mousedown="${(e) => e.stopPropagation()}"
                @click="${(e) => this.toggleExpand(e)}"
                aria-expanded="${this.panelInfo.expanded}"
                aria-controls="content"
                aria-label="Expand ${this.panelInfo.header}">
                ${this.panelInfo.expanded ? r$1.chevronDown : r$1.chevronRight}
              </button>` : O}
          <h2 id="${this.panelInfo.tag}-title" part="title">
            <button
              part="title-button"
              @dblclick="${(e) => {
      this.toggleExpand(e), this.startDraggingDebounce.clear();
    }}">
              ${this.panelInfo.header}
            </button>
          </h2>
          <div class="actions" @mousedown="${(e) => e.stopPropagation()}">${this.renderActions()}</div>
          ${this.renderHelpButton()} ${this.renderPopupButton()}
        </div>
        <div part="content" id="content">
          <slot name="content"></slot>
        </div>
        <div part="drawer-resize"></div>
      </div>
    ` : O;
  }
  getPopupButtonIcon() {
    return this.panelInfo ? this.panelInfo.panel === void 0 ? r$1.close : this.panelInfo.floating ? this.panelInfo.panel === "bottom" ? r$1.dockBottom : this.panelInfo.panel === "left" ? r$1.dockLeft : this.panelInfo.panel === "right" ? r$1.dockRight : O : r$1.popup : O;
  }
  renderHelpButton() {
    var _a2;
    return ((_a2 = this.panelInfo) == null ? void 0 : _a2.helpUrl) ? ct` <button
      @click="${() => window.open(this.panelInfo.helpUrl, "_blank")}"
      @mousedown="${(e) => e.stopPropagation()}"
      title="More information about ${this.panelInfo.header}"
      aria-label="More information about ${this.panelInfo.header}">
      ${r$1.help}
    </button>` : O;
  }
  renderActions() {
    var _a2;
    if (!((_a2 = this.panelInfo) == null ? void 0 : _a2.actionsTag))
      return O;
    const e = this.panelInfo.actionsTag;
    return Il(`<${e}></${e}>`);
  }
  changeDockingPanel(e) {
    var _a2;
    const t = e.detail.value.panel;
    if (((_a2 = this.panelInfo) == null ? void 0 : _a2.panel) !== t) {
      const o = $.panels.filter((i) => i.panel === t).map((i) => i.panelOrder).sort((i, n) => n - i)[0];
      ou(this), $.updatePanel(this.panelInfo.tag, { panel: t, panelOrder: o + 1 });
    }
    this.panelInfo.floating && this.changePanelFloating(e);
  }
  getResizeDirections() {
    const e = this.getAttribute(m);
    return e ? e.split(" ") : [];
  }
};
L([
  h()
], z.prototype, "panelTag", 2);
L([
  v(".drag-handle")
], z.prototype, "headerDraggableArea", 2);
L([
  v("#content")
], z.prototype, "contentArea", 2);
L([
  v('[part="drawer-resize"]')
], z.prototype, "drawerResizeElement", 2);
L([
  v('[part="container"]')
], z.prototype, "container", 2);
L([
  g()
], z.prototype, "dockingItems", 2);
z = L([
  cl("copilot-section-panel-wrapper")
], z);
function Qe(e) {
  p$1.setOperationWaitsHmrUpdate(e, 3e4);
}
A.on("undoRedo", (e) => {
  const o = { files: e.detail.files ?? jc(), uiId: Wc() }, i = e.detail.undo ? "copilot-plugin-undo" : "copilot-plugin-redo", n = e.detail.undo ? "undo" : "redo";
  dn(n), Qe(eu.RedoUndo), A.send(i, o);
});
var it = (e, t, o, i) => {
  for (var n = t, a = e.length - 1, r; a >= 0; a--)
    (r = e[a]) && (n = r(n) || n);
  return n;
};
let ie = class extends Pl {
  static get styles() {
    return [
      le(wc),
      le(mc),
      ul`
        :host {
          --lumo-secondary-text-color: var(--dev-tools-text-color);
          --lumo-contrast-80pct: var(--dev-tools-text-color-emphasis);
          --lumo-contrast-60pct: var(--dev-tools-text-color-secondary);
          --lumo-font-size-m: 14px;

          position: fixed;
          bottom: 2.5rem;
          right: 0rem;
          visibility: visible; /* Always show, even if copilot is off */
          user-select: none;
          z-index: 10000;

          --dev-tools-text-color: rgba(255, 255, 255, 0.8);

          --dev-tools-text-color-secondary: rgba(255, 255, 255, 0.65);
          --dev-tools-text-color-emphasis: rgba(255, 255, 255, 0.95);
          --dev-tools-text-color-active: rgba(255, 255, 255, 1);

          --dev-tools-background-color-inactive: rgba(45, 45, 45, 0.25);
          --dev-tools-background-color-active: rgba(45, 45, 45, 0.98);
          --dev-tools-background-color-active-blurred: rgba(45, 45, 45, 0.85);

          --dev-tools-border-radius: 0.5rem;
          --dev-tools-box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.05), 0 4px 12px -2px rgba(0, 0, 0, 0.4);

          --dev-tools-blue-hsl: 206, 100%, 70%;
          --dev-tools-blue-color: hsl(var(--dev-tools-blue-hsl));
          --dev-tools-green-hsl: 145, 80%, 42%;
          --dev-tools-green-color: hsl(var(--dev-tools-green-hsl));
          --dev-tools-grey-hsl: 0, 0%, 50%;
          --dev-tools-grey-color: hsl(var(--dev-tools-grey-hsl));
          --dev-tools-yellow-hsl: 38, 98%, 64%;
          --dev-tools-yellow-color: hsl(var(--dev-tools-yellow-hsl));
          --dev-tools-red-hsl: 355, 100%, 68%;
          --dev-tools-red-color: hsl(var(--dev-tools-red-hsl));

          /* Needs to be in ms, used in JavaScript as well */
          --dev-tools-transition-duration: 180ms;
        }

        .notification-tray {
          display: flex;
          flex-direction: column-reverse;
          align-items: flex-end;
          margin: 0.5rem;
          flex: none;
        }

        @supports (backdrop-filter: blur(1px)) {
          .notification-tray div.message {
            backdrop-filter: blur(8px);
          }

          .notification-tray div.message {
            background-color: var(--dev-tools-background-color-active-blurred);
          }
        }

        .notification-tray .message {
          pointer-events: auto;
          background-color: var(--dev-tools-background-color-active);
          color: var(--dev-tools-text-color);
          max-width: 40rem;
          box-sizing: border-box;
          border-radius: var(--dev-tools-border-radius);
          margin-top: 0.5rem;
          transition: var(--dev-tools-transition-duration);
          transform-origin: bottom right;
          animation: slideIn var(--dev-tools-transition-duration);
          box-shadow: var(--dev-tools-box-shadow);
          padding-top: 0.25rem;
          padding-bottom: 0.25rem;
        }

        .notification-tray .message.animate-out {
          animation: slideOut forwards var(--dev-tools-transition-duration);
        }

        .notification-tray .message .message-details {
          word-break: break-all;
        }

        .message.information {
          --dev-tools-notification-color: var(--dev-tools-blue-color);
        }

        .message.warning {
          --dev-tools-notification-color: var(--dev-tools-yellow-color);
        }

        .message.error {
          --dev-tools-notification-color: var(--dev-tools-red-color);
        }

        .message {
          display: flex;
          padding: 0.1875rem 0.75rem 0.1875rem 2rem;
          background-clip: padding-box;
        }

        .message.log {
          padding-left: 0.75rem;
        }

        .message-content {
          max-width: 100%;
          margin-right: 0.5rem;
          -webkit-user-select: text;
          -moz-user-select: text;
          user-select: text;
        }

        .message-heading {
          position: relative;
          display: flex;
          align-items: center;
          margin: 0.125rem 0;
        }

        .message .message-details {
          font-weight: 400;
          color: var(--dev-tools-text-color-secondary);
          margin: 0.25rem 0;
          display: flex;
          flex-direction: column;
        }

        .message .message-details[hidden] {
          display: none;
        }

        .message .message-details p {
          display: inline;
          margin: 0;
          margin-right: 0.375em;
          word-break: break-word;
        }

        .message .persist {
          color: var(--dev-tools-text-color-secondary);
          white-space: nowrap;
          margin: 0.375rem 0;
          display: flex;
          align-items: center;
          position: relative;
          -webkit-user-select: none;
          -moz-user-select: none;
          user-select: none;
        }

        .message .persist::before {
          content: '';
          width: 1em;
          height: 1em;
          border-radius: 0.2em;
          margin-right: 0.375em;
          background-color: rgba(255, 255, 255, 0.3);
        }

        .message .persist:hover::before {
          background-color: rgba(255, 255, 255, 0.4);
        }

        .message .persist.on::before {
          background-color: rgba(255, 255, 255, 0.9);
        }

        .message .persist.on::after {
          content: '';
          order: -1;
          position: absolute;
          width: 0.75em;
          height: 0.25em;
          border: 2px solid var(--dev-tools-background-color-active);
          border-width: 0 0 2px 2px;
          transform: translate(0.05em, -0.05em) rotate(-45deg) scale(0.8, 0.9);
        }

        .message .dismiss-message {
          font-weight: 600;
          align-self: stretch;
          display: flex;
          align-items: center;
          padding: 0 0.25rem;
          margin-left: 0.5rem;
          color: var(--dev-tools-text-color-secondary);
        }

        .message .dismiss-message:hover {
          color: var(--dev-tools-text-color);
        }

        .message.log {
          color: var(--dev-tools-text-color-secondary);
        }

        .message:not(.log) .message-heading {
          font-weight: 500;
        }

        .message.has-details .message-heading {
          color: var(--dev-tools-text-color-emphasis);
          font-weight: 600;
        }

        .message-heading::before {
          position: absolute;
          margin-left: -1.5rem;
          display: inline-block;
          text-align: center;
          font-size: 0.875em;
          font-weight: 600;
          line-height: calc(1.25em - 2px);
          width: 14px;
          height: 14px;
          box-sizing: border-box;
          border: 1px solid transparent;
          border-radius: 50%;
        }

        .message.information .message-heading::before {
          content: 'i';
          border-color: currentColor;
          color: var(--dev-tools-notification-color);
        }

        .message.warning .message-heading::before,
        .message.error .message-heading::before {
          content: '!';
          color: var(--dev-tools-background-color-active);
          background-color: var(--dev-tools-notification-color);
        }

        .ahreflike {
          font-weight: 500;
          color: var(--dev-tools-text-color-secondary);
          text-decoration: underline;
          cursor: pointer;
        }

        @keyframes slideIn {
          from {
            transform: translateX(100%);
            opacity: 0;
          }
          to {
            transform: translateX(0%);
            opacity: 1;
          }
        }

        @keyframes slideOut {
          from {
            transform: translateX(0%);
            opacity: 1;
          }
          to {
            transform: translateX(100%);
            opacity: 0;
          }
        }

        @keyframes fade-in {
          0% {
            opacity: 0;
          }
        }

        @keyframes bounce {
          0% {
            transform: scale(0.8);
          }
          50% {
            transform: scale(1.5);
            background-color: hsla(var(--dev-tools-red-hsl), 1);
          }
          100% {
            transform: scale(1);
          }
        }
      `
    ];
  }
  render() {
    return ct`<div class="notification-tray">
      ${p$1.notifications.map((e) => this.renderNotification(e))}
    </div>`;
  }
  renderNotification(e) {
    return ct`
      <div
        class="message ${e.type} ${e.animatingOut ? "animate-out" : ""} ${e.details || e.link ? "has-details" : ""}"
        data-testid="message">
        <div class="message-content">
          <div class="message-heading">${e.message}</div>
          <div class="message-details" ?hidden="${!e.details && !e.link}">
            ${qc(e.details)}
            ${e.link ? ct`<a class="ahreflike" href="${e.link}" target="_blank">Learn more</a>` : ""}
          </div>
          ${e.dismissId ? ct`<div
                class="persist ${e.dontShowAgain ? "on" : "off"}"
                @click=${() => {
      this.toggleDontShowAgain(e);
    }}>
                ${nt(e)}
              </div>` : ""}
        </div>
        <div
          class="dismiss-message"
          @click=${(t) => {
      ho(e), t.stopPropagation();
    }}>
          Dismiss
        </div>
      </div>
    `;
  }
  toggleDontShowAgain(e) {
    e.dontShowAgain = !e.dontShowAgain, this.requestUpdate();
  }
};
ie = it([
  cl("copilot-notifications-container")
], ie);
function nt(e) {
  return e.dontShowAgainMessage ? e.dontShowAgainMessage : "Do not show this again";
}
po({
  type: Xe$1.WARNING,
  message: "Development Mode",
  details: "This application is running in development mode.",
  dismissId: "devmode"
});
const W = xc(async () => {
  await Tc();
});
A.on("vite-after-update", () => {
  W();
});
const ne = (_b = (_a = window == null ? void 0 : window.Vaadin) == null ? void 0 : _a.connectionState) == null ? void 0 : _b.stateChangeListeners;
ne ? ne.add((e, t) => {
  e === "loading" && t === "connected" && p$1.active && W();
}) : console.warn("Unable to add listener for connection state changes");
A.on("copilot-plugin-state", (e) => {
  p$1.setIdePluginState(e.detail), e.detail.active && dn("plugin-active", { pluginVersion: e.detail.version, ide: e.detail.ide }), e.preventDefault();
});
A.on("location-changed", (e) => {
  W();
});
A.on("copilot-ide-notification", (e) => {
  po({
    type: Xe$1[e.detail.type],
    message: e.detail.message,
    dismissId: e.detail.dismissId
  }), e.preventDefault();
});
