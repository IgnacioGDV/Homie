import { c as ct, p as p$1, d as dn, g as po, X as Xe, a as cl } from "./indexhtml-DiL04ybs.js";
import { o } from "./base-panel-BPFs0JUr-DEFdrZ1U.js";
import { r as r$1 } from "./icons-CenXfqbT-Bb6E__cS.js";
const m = "copilot-features-panel{padding:var(--space-100);font:var(--font-xsmall);display:grid;grid-template-columns:auto 1fr;gap:var(--space-50);height:auto}copilot-features-panel a{display:flex;align-items:center;gap:var(--space-50);white-space:nowrap}copilot-features-panel a svg{height:12px;width:12px;min-height:12px;min-width:12px}";
var w = (t, e, a, l) => {
  for (var o2 = e, s = t.length - 1, n; s >= 0; s--)
    (n = t[s]) && (o2 = n(o2) || o2);
  return o2;
};
const i = window.Vaadin.devTools;
let p = class extends o {
  render() {
    return ct` <style>
        ${m}
      </style>
      ${p$1.featureFlags.map(
      (t) => ct`
          <copilot-toggle-button
            .title="${t.title}"
            ?checked=${t.enabled}
            @on-change=${(e) => this.toggleFeatureFlag(e, t)}>
          </copilot-toggle-button>
          <a class="ahreflike" href="${t.moreInfoLink}" title="Learn more" target="_blank"
            >learn more ${r$1.linkExternal}</a
          >
        `
    )}`;
  }
  toggleFeatureFlag(t, e) {
    const a = t.target.checked;
    dn("use-feature", { source: "toggle", enabled: a, id: e.id }), i.frontendConnection ? (i.frontendConnection.send("setFeature", { featureId: e.id, enabled: a }), po({
      type: Xe.INFORMATION,
      message: `“${e.title}” ${a ? "enabled" : "disabled"}`,
      details: e.requiresServerRestart ? "This feature requires a server restart" : void 0,
      dismissId: `feature${e.id}${a ? "Enabled" : "Disabled"}`
    })) : i.log("error", `Unable to toggle feature ${e.title}: No server connection available`);
  }
};
p = w([
  cl("copilot-features-panel")
], p);
const x = {
  header: "Features",
  expanded: false,
  panelOrder: 35,
  panel: "right",
  floating: false,
  tag: "copilot-features-panel",
  helpUrl: "https://vaadin.com/docs/latest/flow/configuration/feature-flags"
}, F = {
  init(t) {
    t.addPanel(x);
  }
};
window.Vaadin.copilot.plugins.push(F);
export {
  p as CopilotFeaturesPanel
};
