import { p as p$1, y as yo, m as mo, q as qc, c as ct, X as Xe, Q as Qs, d as dn, P as Pl, A as A$1, l as ln, a as cl } from "./indexhtml-DiL04ybs.js";
import { g as g$1 } from "./state-CpHuw1-w-Bp2DjuZw.js";
import { o } from "./base-panel-BPFs0JUr-DEFdrZ1U.js";
import { r as r$1 } from "./icons-CenXfqbT-Bb6E__cS.js";
const q = "copilot-log-panel{padding:var(--space-100);font:var(--font-xsmall);display:flex;flex-direction:column;gap:var(--space-50);overflow-y:auto;max-width:100vw}copilot-log-panel .row{display:flex;align-items:flex-start;padding:var(--space-50) var(--space-100);border-radius:var(--radius-2);gap:var(--space-100)}copilot-log-panel .row.information{background-color:var(--blue-50)}copilot-log-panel .row.warning{background-color:var(--yellow-50)}copilot-log-panel .row.error{background-color:var(--red-50)}copilot-log-panel .type{margin-top:var(--space-25)}copilot-log-panel .type.error{color:var(--red)}copilot-log-panel .type.warning{color:var(--yellow)}copilot-log-panel .type.info{color:var(--color)}copilot-log-panel .message{display:flex;flex-direction:column;flex-grow:1;gap:var(--space-25);overflow:hidden}copilot-log-panel .message>*:not(.expanded){white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:100%}copilot-log-panel .message>.expanded{overflow-wrap:break-word}copilot-log-panel .firstrow{display:flex;align-items:baseline;gap:.5em;flex-direction:column}copilot-log-panel .firstrowmessage{width:100%;overflow:hidden;text-overflow:ellipsis}copilot-log-panel button{padding:0;border:0;background:transparent}copilot-log-panel svg{height:12px;width:12px}copilot-log-panel .secondrow,copilot-log-panel .timestamp{font-size:var(--font-size-0);line-height:var(--line-height-1)}copilot-log-panel .expand span{height:12px;width:12px}";
var A = Object.defineProperty, B = Object.getOwnPropertyDescriptor, h = (e, t, a, o2) => {
  for (var s = o2 > 1 ? void 0 : o2 ? B(t, a) : t, d = e.length - 1, i; d >= 0; d--)
    (i = e[d]) && (s = (o2 ? i(t, a, s) : i(s)) || s);
  return o2 && s && A(t, a, s), s;
};
class _ {
  constructor() {
    this.showTimestamps = false, ln(this);
  }
  toggleShowTimestamps() {
    this.showTimestamps = !this.showTimestamps;
  }
}
const g = new _();
let r = class extends o {
  constructor() {
    super(...arguments), this.unreadErrors = false, this.messages = [], this.nextMessageId = 1, this.transitionDuration = 0, this.errorHandlersAdded = false;
  }
  connectedCallback() {
    super.connectedCallback(), this.onCommand("log", (e) => {
      this.handleLogEventData({ type: e.data.type, message: e.data.message });
    }), this.onEventBus("log", (e) => this.handleLogEvent(e)), this.onEventBus("update-log", (e) => this.updateLog(e.detail)), this.onEventBus("notification-shown", (e) => this.handleNotification(e)), this.onEventBus("clear-log", () => this.clear()), this.reaction(
      () => p$1.sectionPanelResizing,
      () => {
        this.requestUpdate();
      }
    ), this.transitionDuration = parseInt(
      window.getComputedStyle(this).getPropertyValue("--dev-tools-transition-duration"),
      10
    ), this.errorHandlersAdded || (yo((e) => {
      this.log(e.type, e.message, !!e.internal, e.details, e.link);
    }), mo.forEach((e) => {
      this.log(e.type, e.message, !!e.internal, e.details, e.link);
    }), mo.length = 0, this.errorHandlersAdded = true);
  }
  clear() {
    this.messages = [];
  }
  handleNotification(e) {
    this.log(e.detail.type, e.detail.message, true, e.detail.details, e.detail.link);
  }
  handleLogEvent(e) {
    this.handleLogEventData(e.detail);
  }
  handleLogEventData(e) {
    this.log(
      e.type,
      e.message,
      !!e.internal,
      e.details,
      e.link,
      qc(e.expandedMessage),
      qc(e.expandedDetails),
      e.id
    );
  }
  activate() {
    this.unreadErrors = false, this.updateComplete.then(() => {
      const e = this.renderRoot.querySelector(".message:last-child");
      e && e.scrollIntoView();
    });
  }
  render() {
    return ct`<style>
        ${q}
      </style>
      ${this.messages.map((e) => this.renderMessage(e))} `;
  }
  renderMessage(e) {
    let t, a, o2;
    return e.type === Xe.ERROR ? (t = "error", o2 = r$1.exclamationMark, a = "Error") : e.type === Xe.WARNING ? (t = "warning", o2 = r$1.warning, a = "Warning") : (t = "info", o2 = r$1.info, a = "Info"), e.internal && (t += " internal"), ct`
      <div
        data-id="${e.id}"
        class="row ${e.type} ${e.details || e.link ? "has-details" : ""}">
        <span class="type ${t}" title="${a}">${o2}</span>
        <div class="message" @click=${() => this.toggleExpanded(e)}>
          <span class="firstrow">
            <span class="timestamp" ?hidden=${!g.showTimestamps}>${U(e.timestamp)}</span>
            <span class="firstrowmessage"
              >${e.expanded && e.expandedMessage ? e.expandedMessage : e.message}
            </span>
          </span>
          ${e.expanded ? ct` <span class="secondrow expanded">${e.expandedDetails ?? e.details}</span>` : ct`<span class="secondrow" ?hidden="${!e.details && !e.link}"
                >${qc(e.details)}
                ${e.link ? ct`<a class="ahreflike" href="${e.link}" target="_blank">Learn more</a>` : ""}</span
              >`}
        </div>
        <button
          aria-label="Expand details"
          theme="icon tertiary"
          class="expand"
          @click=${() => this.toggleExpanded(e)}
          ?hidden=${!this.canBeExpanded(e)}>
          <span>${e.expanded ? r$1.chevronDown : r$1.chevronRight}</span>
        </button>
      </div>
    `;
  }
  log(e, t, a, o2, s, d, i, E) {
    const k = this.nextMessageId;
    this.nextMessageId += 1, i || (i = t);
    const f = {
      id: k,
      type: e,
      message: t,
      details: o2,
      link: s,
      dontShowAgain: false,
      deleted: false,
      expanded: false,
      expandedMessage: d,
      expandedDetails: i,
      timestamp: /* @__PURE__ */ new Date(),
      internal: a,
      userId: E
    };
    for (this.messages.push(f); this.messages.length > r.MAX_LOG_ROWS; )
      this.messages.shift();
    return this.requestUpdate(), this.updateComplete.then(() => {
      const m = this.renderRoot.querySelector(".message:last-child");
      m ? (setTimeout(() => m.scrollIntoView({ behavior: "smooth" }), this.transitionDuration), this.unreadErrors = false) : e === Xe.ERROR && (this.unreadErrors = true);
    }), f;
  }
  updateLog(e) {
    let t = this.messages.find((a) => a.userId === e.id);
    t || (t = this.log(Xe.INFORMATION, "<Log message to update was not found>", false)), Object.assign(t, e), Qs(t.expandedDetails) && (t.expandedDetails = qc(t.expandedDetails)), this.requestUpdate();
  }
  updated() {
    var _a;
    const e = this.querySelector(".row:last-child");
    e && this.isTooLong(e.querySelector(".firstrowmessage")) && ((_a = e.querySelector("button.expand")) == null ? void 0 : _a.removeAttribute("hidden"));
  }
  toggleExpanded(e) {
    this.canBeExpanded(e) && (e.expanded = !e.expanded, this.requestUpdate()), dn("use-log", { source: "toggleExpanded" });
  }
  canBeExpanded(e) {
    var _a;
    if (e.expandedMessage || e.expanded)
      return true;
    const t = (_a = this.querySelector(`[data\\-id="${e.id}"]`)) == null ? void 0 : _a.querySelector(
      ".firstrowmessage"
    );
    return this.isTooLong(t);
  }
  isTooLong(e) {
    return e && e.offsetWidth < e.scrollWidth;
  }
};
r.MAX_LOG_ROWS = 1e3;
h([
  g$1()
], r.prototype, "unreadErrors", 2);
h([
  g$1()
], r.prototype, "messages", 2);
r = h([
  cl("copilot-log-panel")
], r);
let x = class extends Pl {
  createRenderRoot() {
    return this;
  }
  connectedCallback() {
    super.connectedCallback(), this.style.display = "flex";
  }
  render() {
    return ct`
      <button title="Clear log" aria-label="Clear log" theme="icon tertiary">
        <span
          @click=${() => {
      A$1.emit("clear-log", {});
    }}
          >${r$1.trash}</span
        >
      </button>
      <button title="Toggle timestamps" aria-label="Toggle timestamps" theme="icon tertiary">
        <span
          class="${g.showTimestamps ? "on" : "off"}"
          @click=${() => {
      g.toggleShowTimestamps();
    }}
          >${r$1.clock}</span
        >
      </button>
    `;
  }
};
x = h([
  cl("copilot-log-panel-actions")
], x);
const b = {
  header: "Log",
  expanded: true,
  panelOrder: 0,
  panel: "bottom",
  floating: false,
  tag: "copilot-log-panel",
  actionsTag: "copilot-log-panel-actions"
}, P = {
  init(e) {
    e.addPanel(b);
  }
};
window.Vaadin.copilot.plugins.push(P);
const v = { hour: "numeric", minute: "numeric", second: "numeric", fractionalSecondDigits: 3 };
let u;
try {
  u = new Intl.DateTimeFormat(navigator.language, v);
} catch (e) {
  console.error("Failed to create date time formatter for ", navigator.language, e), u = new Intl.DateTimeFormat("en-US", v);
}
function U(e) {
  return u.format(e);
}
export {
  x as Actions,
  r as CopilotLogPanel
};
