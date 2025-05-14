import { k as $c, a as cl, A, c as ct, O, I as Il, n as iu } from "./indexhtml-DiL04ybs.js";
import { o } from "./base-panel-BPFs0JUr-DEFdrZ1U.js";
import { r as r$1 } from "./icons-CenXfqbT-Bb6E__cS.js";
const v = "copilot-shortcuts-panel{font:var(--font-xsmall);padding:var(--space-200);display:flex;flex-direction:column;gap:var(--space-50)}copilot-shortcuts-panel h3{font:var(--font-xsmall-semibold);margin:0;padding:0}copilot-shortcuts-panel h3:not(:first-of-type){margin-top:var(--space-200)}copilot-shortcuts-panel ul{list-style:none;margin:0;padding:0 var(--space-50);display:flex;flex-direction:column}copilot-shortcuts-panel ul li{display:flex;align-items:center;gap:var(--space-150);padding:var(--space-75) 0}copilot-shortcuts-panel ul li:not(:last-of-type){border-bottom:1px dashed var(--border-color)}copilot-shortcuts-panel ul li svg{height:16px;width:16px}copilot-shortcuts-panel ul li .kbds{flex:1;text-align:right}copilot-shortcuts-panel kbd{display:inline-block;border-radius:var(--radius-1);border:1px solid var(--border-color);min-width:1em;min-height:1em;text-align:center;margin:0 .1em;padding:.25em;box-sizing:border-box;font-size:var(--font-size-1);font-family:var(--font-family);line-height:1}", h = window.Vaadin.copilot.tree;
if (!h)
  throw new Error("Tried to access copilot tree before it was initialized.");
var y = (l, a, n, s) => {
  for (var i = a, r = l.length - 1, c; r >= 0; r--)
    (c = l[r]) && (i = c(i) || i);
  return i;
};
let u = class extends o {
  constructor() {
    super(), this.onTreeUpdated = () => {
      this.requestUpdate();
    };
  }
  connectedCallback() {
    super.connectedCallback(), A.on("copilot-tree-created", this.onTreeUpdated);
  }
  disconnectedCallback() {
    super.disconnectedCallback(), A.off("copilot-tree-created", this.onTreeUpdated);
  }
  render() {
    const l = h.hasFlowComponents();
    return ct`<style>
        ${v}
      </style>
      <h3>Global</h3>
      <ul>
        <li>${r$1.vaadinLogo} Copilot ${t(iu.toggleCopilot)}</li>
        <li>${r$1.terminal} Command window ${t(iu.toggleCommandWindow)}</li>
        <li>${r$1.undo} Undo ${t(iu.undo)}</li>
        <li>${r$1.redo} Redo ${t(iu.redo)}</li>
      </ul>
      <h3>Selected component</h3>
      <ul>
        <li>${r$1.code} Go to source ${t(iu.goToSource)}</li>
        ${l ? ct`<li>${r$1.code} Go to attach source ${t(iu.goToAttachSource)}</li>` : O}
        <li>${r$1.copy} Copy ${t(iu.copy)}</li>
        <li>${r$1.paste} Paste ${t(iu.paste)}</li>
        <li>${r$1.duplicate} Duplicate ${t(iu.duplicate)}</li>
        <li>${r$1.userUp} Select parent ${t(iu.selectParent)}</li>
        <li>${r$1.userLeft} Select previous sibling ${t(iu.selectPreviousSibling)}</li>
        <li>${r$1.userRight} Select first child / next sibling ${t(iu.selectNextSibling)}</li>
        <li>${r$1.trash} Delete ${t(iu.delete)}</li>
      </ul>`;
  }
};
u = y([
  cl("copilot-shortcuts-panel")
], u);
function t(l) {
  return ct`<span class="kbds">${Il(l)}</span>`;
}
const C = $c({
  header: "Keyboard Shortcuts",
  tag: "copilot-shortcuts-panel",
  width: 400,
  height: 550,
  floatingPosition: {
    top: 50,
    left: 50
  }
}), P = {
  init(l) {
    l.addPanel(C);
  }
};
window.Vaadin.copilot.plugins.push(P);
