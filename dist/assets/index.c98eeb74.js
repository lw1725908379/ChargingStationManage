/* empty css                */import{r as g,a3 as y,o as x,q as C,a4 as h,w as t,g as i,i as r,l as s,v as c,a5 as S,e as k}from"./index.9456bf99.js";const w={class:"container"},T={class:"dialog-footer"},b={__name:"index",props:{title:{type:String,default:"-"},width:{type:String,default:"50%"},confirmText:{type:String,default:"\u786E\u5B9A"},cancelText:{type:String,default:"\u53D6\u6D88"},footerHidden:{type:Boolean,default:!1}},emits:["onConfirm","onCancel"],setup(e,{expose:d,emit:f}){const o=g(!1),u=()=>{o.value=!0},a=()=>{o.value=!1},n=f,p=()=>{n("onConfirm")},m=()=>{a(),n("onCancel")};return d({openDialog:u,closeDialog:a}),(v,B)=>{const l=k,_=y;return x(),C(_,{"model-value":o.value,title:e.title,width:e.width,"close-on-click-modal":!1,"append-to-body":"",draggable:"","destroy-on-close":!0,footerHidden:e.footerHidden},h({default:t(()=>[i("div",w,[S(v.$slots,"content")])]),_:2},[e.footerHidden?void 0:{name:"footer",fn:t(()=>[i("span",T,[r(l,{type:"primary",onClick:p},{default:t(()=>[s(c(e.confirmText),1)]),_:1}),r(l,{type:"danger",onClick:m},{default:t(()=>[s(c(e.cancelText),1)]),_:1})])]),key:"0"}]),1032,["model-value","title","width","footerHidden"])}}};export{b as _};
