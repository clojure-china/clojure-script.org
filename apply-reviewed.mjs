import assert from 'node:assert/strict';
import {execFileSync} from 'node:child_process';
import {readFileSync,writeFileSync,mkdirSync} from 'node:fs';
assert.equal(process.env.GITHUB_ACTIONS,'true');assert.equal(process.platform,'linux');
assert.equal(execFileSync('calcit',['-v'],{encoding:'utf8'}).trim(),'0.30.1');
const run=(...a)=>execFileSync('calcit',['calcit.cirru',...a],{encoding:'utf8',maxBuffer:48e6,stdio:['ignore','pipe','pipe']});
const blob=()=>execFileSync('git',['hash-object','calcit.cirru'],{encoding:'utf8'}).trim();
assert.equal(blob(),'7108a167f4057d2900f91fe44cf1892099083155');
const norm=v=>Array.isArray(v)?v.map(norm):v&&typeof v==='object'?Object.fromEntries(Object.entries(v).map(([k,x])=>[k,k==='__edn_set'?x.map(norm).sort((a,b)=>JSON.stringify(a).localeCompare(JSON.stringify(b))):norm(x)])):v;
const q=(...a)=>norm(JSON.parse(run('query',...a,'--format','json')).data);
const input=JSON.parse(readFileSync('reviewed.json','utf8'));
const before={config:norm(input.config.data),namespaces:input.namespaces.map(x=>norm(x.data)),defs:input.definitions.map(x=>norm(x.data))};
assert.equal(before.defs.length,33);assert.equal(before.defs.reduce((n,d)=>n+d.tests.length,0),2);assert.equal(before.defs.reduce((n,d)=>n+d.examples.length,0),0);
assert.deepEqual(q('config'),before.config);assert.deepEqual(q('ns').namespaces.map(n=>q('ns',n)),before.namespaces);
for(const d of before.defs)assert.deepEqual(q('def',d.id),d);
const expected=structuredClone(before),ops=[],sites=[],counts={merge:0,identity:0,css:0,dom:0,watcher:0,host:0,schema:0,languageTrait:0,languageSchema:0,languageDecode:0};
const show=(id,path)=>JSON.parse(run('tree','show',id,'--path','@'+path.join('.'),'--format','json')).tree;
const replace=(id,path,value)=>ops.push(['tree','replace',id,'--path','@'+path.join('.'),'--input-format','json-ast','--code',JSON.stringify(value)]);
const map=['::',"'Map","'Tag","'Dynamic"],dispatch=['::',"'Fn",['{}',[':rest',"'Dynamic"],[':return',"'Unit"],[':args',['[]',"'Dynamic"]]]];
const domHint=['hint-fn',['{}',[':return',"'Unit"],[':args',['[]',map,dispatch]]]];
const state=['::',"'reel.typed/State","'app.schema/Action","'app.schema/Store"];
const watchHint=['hint-fn',['{}',[':return',"'Unit"],[':args',['[]',state,state]]]];
const hostHint=['hint-fn',['{}',[':return',"'Unit"],[':args',['[]',"'js-ffi.browser/EventHost"]]]];
const lengths=new Set([':font-size',':max-width',':height',':padding',':width',':margin-top',':min-height']);
const keywords=new Set([':background-color',':color',':margin',':text-align',':text-decoration',':cursor',':background-size',':background-position',':vertical-align',':display',':overflow',':width']);
function insertHint(id,path,x,hint){assert.equal(x[0],'fn');assert.notEqual(x[2]?.[0],'hint-fn');const p=[...path,2];ops.push(['tree','insert',id,'--path','@'+p.join('.'),'--at','before','--input-format','json-ast','--code',JSON.stringify(hint)]);x.splice(2,0,structuredClone(hint));sites.push({id,path:p,inserted:hint});}
function walk(x,path,id){
  if(!Array.isArray(x)||['quote','quasiquote','comment',';','defmacro'].includes(x[0]))return x;
  const original=structuredClone(x),identity=x[0]==='merge'&&x.length===2;
  if(x[0]==='merge'){assert.deepEqual(show(id,path),original);if(identity){assert.equal(id,'app.comp.container/comp-tools');assert.equal(x[1][0],'{}');}else{replace(id,[...path,0],'.merge');x[0]='.merge';counts.merge++;}}
  if(x[0]==='{}')for(let j=1;j<x.length;j++){
    const pair=x[j];if(!Array.isArray(pair)||pair.length!==2)continue;const [k,v]=pair;let next;
    if(lengths.has(k)&&typeof v==='string'&&/^\d+$/.test(v))next='|'+v+'px';
    else if(keywords.has(k)&&typeof v==='string'&&v.startsWith(':'))next='|'+v.slice(1);
    else if(k===':font-weight'&&typeof v==='string'&&/^\d+$/.test(v))next='|'+v;
    if(next!==undefined){assert.ok(id.startsWith('app.comp.'));const p=[...path,j,1];assert.equal(show(id,p),v);replace(id,p,next);pair[1]=next;counts.css++;sites.push({id,path:p,before:v,after:next});}
  }
  const dom=id==='app.comp.container/comp-header'&&x[0]==='fn'&&JSON.stringify(x[1])===JSON.stringify(['e','d!']);
  x=x.map((v,i)=>walk(v,[...path,i],id));
  if(dom){assert.deepEqual(show(id,path),original);assert.equal(original[2][0],'d!');insertHint(id,path,x,domHint);counts.dom++;}
  if(x[0]==='add-watch!'){assert.equal(x[1],'*reel');assert.deepEqual(show(id,[...path,3]),original[3]);insertHint(id,[...path,3],x[3],watchHint);counts.watcher++;}
  if(x[0]==='browser/add-event-listener!'){assert.equal(x[1],'|beforeunload');assert.deepEqual(show(id,[...path,2]),original[2]);insertHint(id,[...path,2],x[2],hostHint);counts.host++;}
  if(identity){replace(id,path,x[1]);counts.identity++;return x[1];}
  return x;
}
for(const d of expected.defs)d.code=walk(d.code,[],d.id);
const render=expected.defs.find(d=>d.id==='app.main/render-app!');assert.equal(render.schema,null);
render.schema=structuredClone(before.defs.find(d=>d.id==='app.main/main!').schema);
ops.push(['edit','schema',render.id,'--input-format','json-ast','--code',JSON.stringify(['::',"'Fn",['{}',...render.schema.slice(1).filter(x=>x[0]!==':kind')]])]);counts.schema++;
const trait=expected.defs.find(d=>d.id==='app.util/HighlightHost'),traitPath=[3,1,2,1,1,3];assert.equal(show(trait.id,traitPath),"'JsObject");assert.equal(trait.code[3][0],'.register-language!');trait.code[3][1][2][1][1][3]="'Fn";replace(trait.id,traitPath,"'Fn");counts.languageTrait++;
const language=expected.defs.find(d=>d.id==='app.util/register-language!');const languageArgs=language.schema.find(x=>Array.isArray(x)&&x[0]===':args')[1];assert.deepEqual(languageArgs,['[]',"'String","'JsObject"]);languageArgs[2]="'Fn";
ops.push(['edit','schema',language.id,'--input-format','json-ast','--code',JSON.stringify(['::',"'Fn",['{}',...language.schema.slice(1).filter(x=>x[0]!==':kind')]])]);counts.languageSchema++;
const main=expected.defs.find(d=>d.id==='app.main/main!'),oldMain=before.defs.find(d=>d.id===main.id);assert.deepEqual(show(main.id,[5]),['register-language!','|clojure','clojure-lang']);main.code[5][2]=['decode-map-as','clojure-lang',"'Fn"];replace(main.id,[5,2],main.code[5][2]);counts.languageDecode++;
assert.deepEqual(counts,{merge:6,identity:1,css:53,dom:1,watcher:2,host:1,schema:1,languageTrait:1,languageSchema:1,languageDecode:1});assert.equal(ops.length,68);
for(const d of before.defs){const e=expected.defs.find(x=>x.id===d.id);for(const k of Object.keys(d).filter(k=>k!=='code'&&(![render.id,language.id].includes(d.id)||k!=='schema')))assert.deepEqual(e[k],d[k]);}
for(const id of ['app.schema/Action','app.schema/Store','app.schema/Showcase','app.schema/ToolInfo','app.updater/decode-action','app.updater/updater','app.schema/store','app.main/*reel','app.main/dispatch!','app.main/mount-target','app.main/save-store!','app.comp.showcase/showcases','app.util/highlighter','app.util/inline','app.util/HighlightResult','app.util/supported-langs'])assert.deepEqual(expected.defs.find(d=>d.id===id),before.defs.find(d=>d.id===id));
assert.deepEqual(main.code[6],oldMain.code[6]);assert.equal(main.code[6][0],'match');assert.equal(main.code[7][0],'render-app!');assert.deepEqual(main.code[11],oldMain.code[11]);
const reload=expected.defs.find(d=>d.id==='app.main/reload!'),oldReload=before.defs.find(d=>d.id===reload.id);assert.deepEqual(reload.code[3][2][5],oldReload.code[3][2][5]);assert.deepEqual(reload.code[3][3],oldReload.code[3][3]);
mkdirSync('acceptance',{recursive:true});for(const [name,value]of Object.entries({before,expected,sites,operations:ops}))writeFileSync('acceptance/'+name+'.json',JSON.stringify(value,null,2));
const txn=['edit','transaction','--file','acceptance/operations.json','--format','json'],preview=JSON.parse(run(...txn,'--dry-run'));assert.ok(preview.scoped_revision);writeFileSync('acceptance/preview.json',JSON.stringify(preview,null,2));writeFileSync('acceptance/applied.json',run(...txn,'--expect-revision',preview.scoped_revision));
const after={config:q('config'),namespaces:q('ns').namespaces.map(n=>q('ns',n)),defs:expected.defs.map(d=>q('def',d.id))};assert.deepEqual(after,expected);
writeFileSync('acceptance/after.json',JSON.stringify(after,null,2));writeFileSync('acceptance/snapshot-after-blob.txt',blob());
writeFileSync('acceptance/summary.json',JSON.stringify({definitions:33,tests:2,examples:0,operations:68,counts,unsafeBefore:0,unsafeAfter:0,preserved:'Publicschemasexceptpreviouslymissingrender-app!Unitjsffiandregister-language!languageFnnotfakeJsObject;matchingHighlightHostparameterFn. ImportedlanguagecheckeddecodebareFn,notconcretecallableproof. Imports/metadata/config/original2tests/Store/Action/decoder/updater/showcasecontents/randompick/highlighter/mainmjs/HUD/restorefirst remain. Nohelpers/casts/budgets/tests/frameworks/compiler rules.'},null,2));
