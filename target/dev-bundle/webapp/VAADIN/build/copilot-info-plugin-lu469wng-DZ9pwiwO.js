import { r as ru, b as Po, d as dn, e as du, p as p$1, c as ct, C as Cc, O, f as fu, u as uu, g as po, X as Xe, P as Pl, A, h as Oc, a as cl } from "./indexhtml-DiL04ybs.js";
import { g } from "./state-CpHuw1-w-Bp2DjuZw.js";
import { o } from "./base-panel-BPFs0JUr-DEFdrZ1U.js";
import { r as r$1 } from "./icons-CenXfqbT-Bb6E__cS.js";
const W = "copilot-info-panel{--dev-tools-red-color: red;--dev-tools-grey-color: gray;--dev-tools-green-color: green;position:relative}copilot-info-panel div.info-tray{display:flex;flex-direction:column;gap:10px}copilot-info-panel vaadin-button{margin-inline:var(--lumo-space-l)}copilot-info-panel dl{display:grid;grid-template-columns:auto auto;gap:0;margin:var(--space-100) var(--space-50);font:var(--font-xsmall)}copilot-info-panel dl>dt,copilot-info-panel dl>dd{padding:3px 10px;margin:0;white-space:nowrap;overflow:hidden;text-overflow:ellipsis}copilot-info-panel dd.live-reload-status>span{overflow:hidden;text-overflow:ellipsis;display:block;color:var(--status-color)}copilot-info-panel dd span.hidden{display:none}copilot-info-panel dd span.true{color:var(--dev-tools-green-color);font-size:large}copilot-info-panel dd span.false{color:var(--dev-tools-red-color);font-size:large}copilot-info-panel code{white-space:nowrap;-webkit-user-select:all;user-select:all}copilot-info-panel .checks{display:inline-grid;grid-template-columns:auto 1fr;gap:var(--space-50)}copilot-info-panel span.hint{font-size:var(--font-size-0);background:var(--gray-50);padding:var(--space-75);border-radius:var(--radius-2)}";
var D, E;
function _() {
  return E || (E = 1, D = function() {
    var e = document.getSelection();
    if (!e.rangeCount)
      return function() {
      };
    for (var t = document.activeElement, n = [], i = 0; i < e.rangeCount; i++)
      n.push(e.getRangeAt(i));
    switch (t.tagName.toUpperCase()) {
      // .toUpperCase handles XHTML
      case "INPUT":
      case "TEXTAREA":
        t.blur();
        break;
      default:
        t = null;
        break;
    }
    return e.removeAllRanges(), function() {
      e.type === "Caret" && e.removeAllRanges(), e.rangeCount || n.forEach(function(l) {
        e.addRange(l);
      }), t && t.focus();
    };
  }), D;
}
var k, $;
function z() {
  if ($) return k;
  $ = 1;
  var e = _(), t = {
    "text/plain": "Text",
    "text/html": "Url",
    default: "Text"
  }, n = "Copy to clipboard: #{key}, Enter";
  function i(a) {
    var o2 = (/mac os x/i.test(navigator.userAgent) ? "⌘" : "Ctrl") + "+C";
    return a.replace(/#{\s*key\s*}/g, o2);
  }
  function l(a, o2) {
    var s, u, f, v, p, r, h = false;
    o2 || (o2 = {}), s = o2.debug || false;
    try {
      f = e(), v = document.createRange(), p = document.getSelection(), r = document.createElement("span"), r.textContent = a, r.ariaHidden = "true", r.style.all = "unset", r.style.position = "fixed", r.style.top = 0, r.style.clip = "rect(0, 0, 0, 0)", r.style.whiteSpace = "pre", r.style.webkitUserSelect = "text", r.style.MozUserSelect = "text", r.style.msUserSelect = "text", r.style.userSelect = "text", r.addEventListener("copy", function(c) {
        if (c.stopPropagation(), o2.format)
          if (c.preventDefault(), typeof c.clipboardData > "u") {
            s && console.warn("unable to use e.clipboardData"), s && console.warn("trying IE specific stuff"), window.clipboardData.clearData();
            var x = t[o2.format] || t.default;
            window.clipboardData.setData(x, a);
          } else
            c.clipboardData.clearData(), c.clipboardData.setData(o2.format, a);
        o2.onCopy && (c.preventDefault(), o2.onCopy(c.clipboardData));
      }), document.body.appendChild(r), v.selectNodeContents(r), p.addRange(v);
      var R = document.execCommand("copy");
      if (!R)
        throw new Error("copy command was unsuccessful");
      h = true;
    } catch (c) {
      s && console.error("unable to copy using execCommand: ", c), s && console.warn("trying IE specific stuff");
      try {
        window.clipboardData.setData(o2.format || "text", a), o2.onCopy && o2.onCopy(window.clipboardData), h = true;
      } catch (x) {
        s && console.error("unable to copy using clipboardData: ", x), s && console.error("falling back to prompt"), u = i("message" in o2 ? o2.message : n), window.prompt(u, a);
      }
    } finally {
      p && (typeof p.removeRange == "function" ? p.removeRange(v) : p.removeAllRanges()), r && document.body.removeChild(r), f();
    }
    return h;
  }
  return k = l, k;
}
var F = z();
const G = /* @__PURE__ */ Oc(F);
var K = Object.defineProperty, X = Object.getOwnPropertyDescriptor, w = (e, t, n, i) => {
  for (var l = i > 1 ? void 0 : i ? X(t, n) : t, a = e.length - 1, o2; a >= 0; a--)
    (o2 = e[a]) && (l = (i ? o2(t, n, l) : o2(l)) || l);
  return i && l && K(t, n, l), l;
};
let b = class extends o {
  constructor() {
    super(...arguments), this.serverInfo = [], this.clientInfo = [{ name: "Browser", version: navigator.userAgent }], this.handleServerInfoEvent = (e) => {
      const t = JSON.parse(e.data.info);
      this.serverInfo = t.versions, ru().then((n) => {
        n && (this.clientInfo.unshift({ name: "Vaadin Employee", version: "true", more: void 0 }), this.requestUpdate("clientInfo"));
      }), Po() === "success" && dn("hotswap-active", { value: du() });
    };
  }
  connectedCallback() {
    super.connectedCallback(), this.onCommand("copilot-info", this.handleServerInfoEvent), this.onEventBus("system-info-with-callback", (e) => {
      e.detail.callback(this.getInfoForClipboard(e.detail.notify));
    }), this.reaction(
      () => p$1.idePluginState,
      () => {
        this.requestUpdate("serverInfo");
      }
    );
  }
  getIndex(e) {
    return this.serverInfo.findIndex((t) => t.name === e);
  }
  render() {
    return ct`<style>
        ${W}
      </style>
      <div class="info-tray">
        <dl>
          ${[...this.serverInfo, ...this.clientInfo].map(
      (e) => ct`
              <dt>${e.name}</dt>
              <dd title="${e.version}" style="${e.name === "Java Hotswap" ? "white-space: normal" : ""}">
                ${this.renderValue(e.version)} ${e.more}
              </dd>
            `
    )}
          ${this.renderDevWorkflowSection()}
        </dl>
        ${this.renderDevelopmentWorkflowButton()}
      </div>`;
  }
  renderDevWorkflowSection() {
    const e = Po(), t = this.getIdePluginLabelText(p$1.idePluginState), n = this.getHotswapAgentLabelText(e);
    return ct`
      <dt>Java Hotswap</dt>
      <dd>${m(e === "success")} ${n}</dd>
      ${Cc() !== "unsupported" ? ct`<dt>IDE Plugin</dt>
            <dd>${m(Cc() === "success")} ${t}</dd>` : O}
    `;
  }
  renderDevelopmentWorkflowButton() {
    const e = fu();
    let t = "", n = null;
    return e.status === "success" ? (t = "More details...", n = r$1.successColorful) : e.status === "warning" ? (t = "Improve Development Workflow...", n = r$1.warningColorful) : e.status === "error" && (t = "Fix Development Workflow...", n = ct`<span style="color: var(--red)">${r$1.error}</span>`), ct`
      <vaadin-button
        id="development-workflow-guide"
        @click="${() => {
      uu();
    }}">
        <span slot="prefix"> ${n}</span>
        ${t}</vaadin-button
      >
    `;
  }
  getHotswapAgentLabelText(e) {
    return e === "success" ? "Java Hotswap is enabled" : e === "error" ? "Hotswap is partially enabled" : "Hotswap is not enabled";
  }
  getIdePluginLabelText(e) {
    if (Cc() !== "success")
      return "Not installed";
    if (e == null ? void 0 : e.version) {
      let t = null;
      return (e == null ? void 0 : e.ide) && ((e == null ? void 0 : e.ide) === "intellij" ? t = "IntelliJ" : (e == null ? void 0 : e.ide) === "vscode" ? t = "VS Code" : (e == null ? void 0 : e.ide) === "eclipse" && (t = "Eclipse")), t ? `${e == null ? void 0 : e.version} ${t}` : e == null ? void 0 : e.version;
    }
    return "Not installed";
  }
  renderValue(e) {
    return e === "false" ? m(false) : e === "true" ? m(true) : e;
  }
  getInfoForClipboard(e) {
    const t = this.renderRoot.querySelectorAll(".info-tray dt"), l = Array.from(t).map((a) => ({
      key: a.textContent.trim(),
      value: a.nextElementSibling.textContent.trim()
    })).filter((a) => a.key !== "Live reload").filter((a) => !a.key.startsWith("Vaadin Emplo")).map((a) => {
      var _a;
      const { key: o2 } = a;
      let { value: s } = a;
      if (o2 === "IDE Plugin")
        s = this.getIdePluginLabelText(p$1.idePluginState) ?? "false";
      else if (o2 === "Java Hotswap") {
        const u = (_a = p$1.jdkInfo) == null ? void 0 : _a.jrebel, f = Po();
        u && f === "success" ? s = "JRebel is in use" : s = this.getHotswapAgentLabelText(f);
      }
      return `${o2}: ${s}`;
    }).join(`
`);
    return e && po({
      type: Xe.INFORMATION,
      message: "Environment information copied to clipboard",
      dismissId: "versionInfoCopied"
    }), l.trim();
  }
};
w([
  g()
], b.prototype, "serverInfo", 2);
w([
  g()
], b.prototype, "clientInfo", 2);
b = w([
  cl("copilot-info-panel")
], b);
let S = class extends Pl {
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.style.display = "flex";
  }
  render() {
    return ct`<button title="Copy to clipboard" aria-label="Copy to clipboard" theme="icon tertiary">
      <span
        @click=${() => {
      A.emit("system-info-with-callback", {
        callback: G,
        notify: true
      });
    }}
        >${r$1.copy}</span
      >
    </button>`;
  }
};
S = w([
  cl("copilot-info-actions")
], S);
const Q = {
  header: "Info",
  expanded: false,
  panelOrder: 15,
  panel: "right",
  floating: false,
  tag: "copilot-info-panel",
  actionsTag: "copilot-info-actions",
  eager: true
  // Render even when collapsed as error handling depends on this
}, Y = {
  init(e) {
    e.addPanel(Q);
  }
};
window.Vaadin.copilot.plugins.push(Y);
function m(e) {
  return e ? ct`<span class="true">☑</span>` : ct`<span class="false">☒</span>`;
}
export {
  S as Actions,
  b as CopilotInfoPanel
};
