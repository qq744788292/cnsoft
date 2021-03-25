(function (global, factory) {
	typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
	typeof define === 'function' && define.amd ? define(factory) :
	(global.wangEditor = factory());
}(this, (function () { 'use strict';

/*
    poly-fill
*/

var polyfill = function () {

    // Object.assign
    if (typeof Object.assign != 'function') {
        Object.assign = function (target, varArgs) {
            // .length of function is 2
            if (target == null) {
                // TypeError if undefined or null
                throw new TypeError('Cannot convert undefined or null to object');
            }

            var to = Object(target);

            for (var index = 1; index < arguments.length; index++) {
                var nextSource = arguments[index];

                if (nextSource != null) {
                    // Skip over if undefined or null
                    for (var nextKey in nextSource) {
                        // Avoid bugs when hasOwnProperty is shadowed
                        if (Object.prototype.hasOwnProperty.call(nextSource, nextKey)) {
                            to[nextKey] = nextSource[nextKey];
                        }
                    }
                }
            }
            return to;
        };
    }

    // IE 涓吋瀹?Element.prototype.matches
    if (!Element.prototype.matches) {
        Element.prototype.matches = Element.prototype.matchesSelector || Element.prototype.mozMatchesSelector || Element.prototype.msMatchesSelector || Element.prototype.oMatchesSelector || Element.prototype.webkitMatchesSelector || function (s) {
            var matches = (this.document || this.ownerDocument).querySelectorAll(s),
                i = matches.length;
            while (--i >= 0 && matches.item(i) !== this) {}
            return i > -1;
        };
    }
};

/*
    DOM 鎿嶄綔 API
*/

// 鏍规嵁 html 浠ｇ爜鐗囨鍒涘缓 dom 瀵硅薄
function createElemByHTML(html) {
    var div = void 0;
    div = document.createElement('div');
    div.innerHTML = html;
    return div.children;
}

// 鏄惁鏄?DOM List
function isDOMList(selector) {
    if (!selector) {
        return false;
    }
    if (selector instanceof HTMLCollection || selector instanceof NodeList) {
        return true;
    }
    return false;
}

// 灏佽 document.querySelectorAll
function querySelectorAll(selector) {
    var result = document.querySelectorAll(selector);
    if (isDOMList(result)) {
        return result;
    } else {
        return [result];
    }
}

// 璁板綍鎵€鏈夌殑浜嬩欢缁戝畾
var eventList = [];

// 鍒涘缓鏋勯€犲嚱鏁?function DomElement(selector) {
    if (!selector) {
        return;
    }

    // selector 鏈潵灏辨槸 DomElement 瀵硅薄锛岀洿鎺ヨ繑鍥?    if (selector instanceof DomElement) {
        return selector;
    }

    this.selector = selector;
    var nodeType = selector.nodeType;

    // 鏍规嵁 selector 寰楀嚭鐨勭粨鏋滐紙濡?DOM锛孌OM List锛?    var selectorResult = [];
    if (nodeType === 9) {
        // document 鑺傜偣
        selectorResult = [selector];
    } else if (nodeType === 1) {
        // 鍗曚釜 DOM 鑺傜偣
        selectorResult = [selector];
    } else if (isDOMList(selector) || selector instanceof Array) {
        // DOM List 鎴栬€呮暟缁?        selectorResult = selector;
    } else if (typeof selector === 'string') {
        // 瀛楃涓?        selector = selector.replace('/\n/mg', '').trim();
        if (selector.indexOf('<') === 0) {
            // 濡?<div>
            selectorResult = createElemByHTML(selector);
        } else {
            // 濡?#id .class
            selectorResult = querySelectorAll(selector);
        }
    }

    var length = selectorResult.length;
    if (!length) {
        // 绌烘暟缁?        return this;
    }

    // 鍔犲叆 DOM 鑺傜偣
    var i = void 0;
    for (i = 0; i < length; i++) {
        this[i] = selectorResult[i];
    }
    this.length = length;
}

// 淇敼鍘熷瀷
DomElement.prototype = {
    constructor: DomElement,

    // 绫绘暟缁勶紝forEach
    forEach: function forEach(fn) {
        var i = void 0;
        for (i = 0; i < this.length; i++) {
            var elem = this[i];
            var result = fn.call(elem, elem, i);
            if (result === false) {
                break;
            }
        }
        return this;
    },

    // clone
    clone: function clone(deep) {
        var cloneList = [];
        this.forEach(function (elem) {
            cloneList.push(elem.cloneNode(!!deep));
        });
        return $(cloneList);
    },

    // 鑾峰彇绗嚑涓厓绱?    get: function get(index) {
        var length = this.length;
        if (index >= length) {
            index = index % length;
        }
        return $(this[index]);
    },

    // 绗竴涓?    first: function first() {
        return this.get(0);
    },

    // 鏈€鍚庝竴涓?    last: function last() {
        var length = this.length;
        return this.get(length - 1);
    },

    // 缁戝畾浜嬩欢
    on: function on(type, selector, fn) {
        // selector 涓嶄负绌猴紝璇佹槑缁戝畾浜嬩欢瑕佸姞浠ｇ悊
        if (!fn) {
            fn = selector;
            selector = null;
        }

        // type 鏄惁鏈夊涓?        var types = [];
        types = type.split(/\s+/);

        return this.forEach(function (elem) {
            types.forEach(function (type) {
                if (!type) {
                    return;
                }

                // 璁板綍涓嬶紝鏂逛究鍚庨潰瑙ｇ粦
                eventList.push({
                    elem: elem,
                    type: type,
                    fn: fn
                });

                if (!selector) {
                    // 鏃犱唬鐞?                    elem.addEventListener(type, fn);
                    return;
                }

                // 鏈変唬鐞?                elem.addEventListener(type, function (e) {
                    var target = e.target;
                    if (target.matches(selector)) {
                        fn.call(target, e);
                    }
                });
            });
        });
    },

    // 鍙栨秷浜嬩欢缁戝畾
    off: function off(type, fn) {
        return this.forEach(function (elem) {
            elem.removeEventListener(type, fn);
        });
    },

    // 鑾峰彇/璁剧疆 灞炴€?    attr: function attr(key, val) {
        if (val == null) {
            // 鑾峰彇鍊?            return this[0].getAttribute(key);
        } else {
            // 璁剧疆鍊?            return this.forEach(function (elem) {
                elem.setAttribute(key, val);
            });
        }
    },

    // 娣诲姞 class
    addClass: function addClass(className) {
        if (!className) {
            return this;
        }
        return this.forEach(function (elem) {
            var arr = void 0;
            if (elem.className) {
                // 瑙ｆ瀽褰撳墠 className 杞崲涓烘暟缁?                arr = elem.className.split(/\s/);
                arr = arr.filter(function (item) {
                    return !!item.trim();
                });
                // 娣诲姞 class
                if (arr.indexOf(className) < 0) {
                    arr.push(className);
                }
                // 淇敼 elem.class
                elem.className = arr.join(' ');
            } else {
                elem.className = className;
            }
        });
    },

    // 鍒犻櫎 class
    removeClass: function removeClass(className) {
        if (!className) {
            return this;
        }
        return this.forEach(function (elem) {
            var arr = void 0;
            if (elem.className) {
                // 瑙ｆ瀽褰撳墠 className 杞崲涓烘暟缁?                arr = elem.className.split(/\s/);
                arr = arr.filter(function (item) {
                    item = item.trim();
                    // 鍒犻櫎 class
                    if (!item || item === className) {
                        return false;
                    }
                    return true;
                });
                // 淇敼 elem.class
                elem.className = arr.join(' ');
            }
        });
    },

    // 淇敼 css
    css: function css(key, val) {
        var currentStyle = key + ':' + val + ';';
        return this.forEach(function (elem) {
            var style = (elem.getAttribute('style') || '').trim();
            var styleArr = void 0,
                resultArr = [];
            if (style) {
                // 灏?style 鎸夌収 ; 鎷嗗垎涓烘暟缁?                styleArr = style.split(';');
                styleArr.forEach(function (item) {
                    // 瀵规瘡椤规牱寮忥紝鎸夌収 : 鎷嗗垎涓?key 鍜?value
                    var arr = item.split(':').map(function (i) {
                        return i.trim();
                    });
                    if (arr.length === 2) {
                        resultArr.push(arr[0] + ':' + arr[1]);
                    }
                });
                // 鏇挎崲鎴栬€呮柊澧?                resultArr = resultArr.map(function (item) {
                    if (item.indexOf(key) === 0) {
                        return currentStyle;
                    } else {
                        return item;
                    }
                });
                if (resultArr.indexOf(currentStyle) < 0) {
                    resultArr.push(currentStyle);
                }
                // 缁撴灉
                elem.setAttribute('style', resultArr.join('; '));
            } else {
                // style 鏃犲€?                elem.setAttribute('style', currentStyle);
            }
        });
    },

    // 鏄剧ず
    show: function show() {
        return this.css('display', 'block');
    },

    // 闅愯棌
    hide: function hide() {
        return this.css('display', 'none');
    },

    // 鑾峰彇瀛愯妭鐐?    children: function children() {
        var elem = this[0];
        if (!elem) {
            return null;
        }

        return $(elem.children);
    },

    // 鑾峰彇瀛愯妭鐐癸紙鍖呮嫭鏂囨湰鑺傜偣锛?    childNodes: function childNodes() {
        var elem = this[0];
        if (!elem) {
            return null;
        }

        return $(elem.childNodes);
    },

    // 澧炲姞瀛愯妭鐐?    append: function append($children) {
        return this.forEach(function (elem) {
            $children.forEach(function (child) {
                elem.appendChild(child);
            });
        });
    },

    // 绉婚櫎褰撳墠鑺傜偣
    remove: function remove() {
        return this.forEach(function (elem) {
            if (elem.remove) {
                elem.remove();
            } else {
                var parent = elem.parentElement;
                parent && parent.removeChild(elem);
            }
        });
    },

    // 鏄惁鍖呭惈鏌愪釜瀛愯妭鐐?    isContain: function isContain($child) {
        var elem = this[0];
        var child = $child[0];
        return elem.contains(child);
    },

    // 灏哄鏁版嵁
    getSizeData: function getSizeData() {
        var elem = this[0];
        return elem.getBoundingClientRect(); // 鍙緱鍒?bottom height left right top width 鐨勬暟鎹?    },

    // 灏佽 nodeName
    getNodeName: function getNodeName() {
        var elem = this[0];
        return elem.nodeName;
    },

    // 浠庡綋鍓嶅厓绱犳煡鎵?    find: function find(selector) {
        var elem = this[0];
        return $(elem.querySelectorAll(selector));
    },

    // 鑾峰彇褰撳墠鍏冪礌鐨?text
    text: function text(val) {
        if (!val) {
            // 鑾峰彇 text
            var elem = this[0];
            return elem.innerHTML.replace(/<.*?>/g, function () {
                return '';
            });
        } else {
            // 璁剧疆 text
            return this.forEach(function (elem) {
                elem.innerHTML = val;
            });
        }
    },

    // 鑾峰彇 html
    html: function html(value) {
        var elem = this[0];
        if (value == null) {
            return elem.innerHTML;
        } else {
            elem.innerHTML = value;
            return this;
        }
    },

    // 鑾峰彇 value
    val: function val() {
        var elem = this[0];
        return elem.value.trim();
    },

    // focus
    focus: function focus() {
        return this.forEach(function (elem) {
            elem.focus();
        });
    },

    // parent
    parent: function parent() {
        var elem = this[0];
        return $(elem.parentElement);
    },

    // parentUntil 鎵惧埌绗﹀悎 selector 鐨勭埗鑺傜偣
    parentUntil: function parentUntil(selector, _currentElem) {
        var results = document.querySelectorAll(selector);
        var length = results.length;
        if (!length) {
            // 浼犲叆鐨?selector 鏃犳晥
            return null;
        }

        var elem = _currentElem || this[0];
        if (elem.nodeName === 'BODY') {
            return null;
        }

        var parent = elem.parentElement;
        var i = void 0;
        for (i = 0; i < length; i++) {
            if (parent === results[i]) {
                // 鎵惧埌锛屽苟杩斿洖
                return $(parent);
            }
        }

        // 缁х画鏌ユ壘
        return this.parentUntil(selector, parent);
    },

    // 鍒ゆ柇涓や釜 elem 鏄惁鐩哥瓑
    equal: function equal($elem) {
        if ($elem.nodeType === 1) {
            return this[0] === $elem;
        } else {
            return this[0] === $elem[0];
        }
    },

    // 灏嗚鍏冪礌鎻掑叆鍒版煇涓厓绱犲墠闈?    insertBefore: function insertBefore(selector) {
        var $referenceNode = $(selector);
        var referenceNode = $referenceNode[0];
        if (!referenceNode) {
            return this;
        }
        return this.forEach(function (elem) {
            var parent = referenceNode.parentNode;
            parent.insertBefore(elem, referenceNode);
        });
    },

    // 灏嗚鍏冪礌鎻掑叆鍒版煇涓厓绱犲悗闈?    insertAfter: function insertAfter(selector) {
        var $referenceNode = $(selector);
        var referenceNode = $referenceNode[0];
        if (!referenceNode) {
            return this;
        }
        return this.forEach(function (elem) {
            var parent = referenceNode.parentNode;
            if (parent.lastChild === referenceNode) {
                // 鏈€鍚庝竴涓厓绱?                parent.appendChild(elem);
            } else {
                // 涓嶆槸鏈€鍚庝竴涓厓绱?                parent.insertBefore(elem, referenceNode.nextSibling);
            }
        });
    }
};

// new 涓€涓璞?function $(selector) {
    return new DomElement(selector);
}

// 瑙ｇ粦鎵€鏈変簨浠讹紝鐢ㄤ簬閿€姣佺紪杈戝櫒
$.offAll = function () {
    eventList.forEach(function (item) {
        var elem = item.elem;
        var type = item.type;
        var fn = item.fn;
        // 瑙ｇ粦
        elem.removeEventListener(type, fn);
    });
};

/*
    閰嶇疆淇℃伅
*/

var config = {

    // 榛樿鑿滃崟閰嶇疆
    menus: ['head', 'bold', 'fontSize', 'fontName', 'italic', 'underline', 'strikeThrough', 'foreColor', 'backColor', 'link', 'list', 'justify', 'quote', 'emoticon', 'image', 'table', 'video', 'code', 'undo', 'redo'],

    fontNames: ['瀹嬩綋', '寰蒋闆呴粦', 'Arial', 'Tahoma', 'Verdana'],

    colors: ['#000000', '#eeece0', '#1c487f', '#4d80bf', '#c24f4a', '#8baa4a', '#7b5ba1', '#46acc8', '#f9963b', '#ffffff'],

    // // 璇█閰嶇疆
    // lang: {
    //     '璁剧疆鏍囬': 'title',
    //     '姝ｆ枃': 'p',
    //     '閾炬帴鏂囧瓧': 'link text',
    //     '閾炬帴': 'link',
    //     '鎻掑叆': 'insert',
    //     '鍒涘缓': 'init'
    // },

    // 琛ㄦ儏
    emotions: [{
        // tab 鐨勬爣棰?        title: '榛樿',
        // type -> 'emoji' / 'image'
        type: 'image',
        // content -> 鏁扮粍
        content: [{
            alt: '[鍧忕瑧]',
            src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png'
        }, {
            alt: '[鑸斿睆]',
            src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/40/pcmoren_tian_org.png'
        }, {
            alt: '[姹',
            src: 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png'
        }]
    }, {
        // tab 鐨勬爣棰?        title: '鏂版氮',
        // type -> 'emoji' / 'image'
        type: 'image',
        // content -> 鏁扮粍
        content: [{
            src: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif',
            alt: '[鑽夋偿椹琞'
        }, {
            src: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif',
            alt: '[绁為┈]'
        }, {
            src: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif',
            alt: '[娴簯]'
        }]
    }, {
        // tab 鐨勬爣棰?        title: 'emoji',
        // type -> 'emoji' / 'image'
        type: 'emoji',
        // content -> 鏁扮粍
        content: '馃榾 馃槂 馃槃 馃榿 馃槅 馃槄 馃槀 馃槉 馃槆 馃檪 馃檭 馃槈 馃槗 馃槳 馃槾 馃檮 馃 馃槵 馃'.split(/\s/)
    }],

    // 缂栬緫鍖哄煙鐨?z-index
    zIndex: 10000,

    // 鏄惁寮€鍚?debug 妯″紡锛坉ebug 妯″紡涓嬮敊璇細 throw error 褰㈠紡鎶涘嚭锛?    debug: false,

    // 鎻掑叆閾炬帴鏃跺€欑殑鏍煎紡鏍￠獙
    linkCheck: function linkCheck(text, link) {
        // text 鏄彃鍏ョ殑鏂囧瓧
        // link 鏄彃鍏ョ殑閾炬帴
        return true; // 杩斿洖 true 鍗宠〃绀烘垚鍔?        // return '鏍￠獙澶辫触' // 杩斿洖瀛楃涓插嵆琛ㄧず澶辫触鐨勬彁绀轰俊鎭?    },

    // 鎻掑叆缃戠粶鍥剧墖鐨勬牎楠?    linkImgCheck: function linkImgCheck(src) {
        // src 鍗冲浘鐗囩殑鍦板潃
        return true; // 杩斿洖 true 鍗宠〃绀烘垚鍔?        // return '鏍￠獙澶辫触'  // 杩斿洖瀛楃涓插嵆琛ㄧず澶辫触鐨勬彁绀轰俊鎭?    },

    // 绮樿创杩囨护鏍峰紡锛岄粯璁ゅ紑鍚?    pasteFilterStyle: true,

    // 绮樿创鍐呭鏃讹紝蹇界暐鍥剧墖銆傞粯璁ゅ叧闂?    pasteIgnoreImg: false,

    // 瀵圭矘璐寸殑鏂囧瓧杩涜鑷畾涔夊鐞嗭紝杩斿洖澶勭悊鍚庣殑缁撴灉銆傜紪杈戝櫒浼氬皢澶勭悊鍚庣殑缁撴灉绮樿创鍒扮紪杈戝尯鍩熶腑銆?    // IE 鏆傛椂涓嶆敮鎸?    pasteTextHandle: function pasteTextHandle(content) {
        // content 鍗崇矘璐磋繃鏉ョ殑鍐呭锛坔tml 鎴?绾枃鏈級锛屽彲杩涜鑷畾涔夊鐞嗙劧鍚庤繑鍥?        return content;
    },

    // onchange 浜嬩欢
    // onchange: function (html) {
    //     // html 鍗冲彉鍖栦箣鍚庣殑鍐呭
    //     console.log(html)
    // },

    // 鏄惁鏄剧ず娣诲姞缃戠粶鍥剧墖鐨?tab
    showLinkImg: true,

    // 鎻掑叆缃戠粶鍥剧墖鐨勫洖璋?    linkImgCallback: function linkImgCallback(url) {
        // console.log(url)  // url 鍗虫彃鍏ュ浘鐗囩殑鍦板潃
    },

    // 榛樿涓婁紶鍥剧墖 max size: 5M
    uploadImgMaxSize: 5 * 1024 * 1024,

    // 閰嶇疆涓€娆℃渶澶氫笂浼犲嚑涓浘鐗?    // uploadImgMaxLength: 5,

    // 涓婁紶鍥剧墖锛屾槸鍚︽樉绀?base64 鏍煎紡
    uploadImgShowBase64: false,

    // 涓婁紶鍥剧墖锛宻erver 鍦板潃锛堝鏋滄湁鍊硷紝鍒?base64 鏍煎紡鐨勯厤缃垯澶辨晥锛?    // uploadImgServer: '/upload',

    // 鑷畾涔夐厤缃?filename
    uploadFileName: '',

    // 涓婁紶鍥剧墖鐨勮嚜瀹氫箟鍙傛暟
    uploadImgParams: {
        // token: 'abcdef12345'
    },

    // 涓婁紶鍥剧墖鐨勮嚜瀹氫箟header
    uploadImgHeaders: {
        // 'Accept': 'text/x-json'
    },

    // 閰嶇疆 XHR withCredentials
    withCredentials: false,

    // 鑷畾涔変笂浼犲浘鐗囪秴鏃舵椂闂?ms
    uploadImgTimeout: 10000,

    // 涓婁紶鍥剧墖 hook 
    uploadImgHooks: {
        // customInsert: function (insertLinkImg, result, editor) {
        //     console.log('customInsert')
        //     // 鍥剧墖涓婁紶骞惰繑鍥炵粨鏋滐紝鑷畾涔夋彃鍏ュ浘鐗囩殑浜嬩欢锛岃€屼笉鏄紪杈戝櫒鑷姩鎻掑叆鍥剧墖
        //     const data = result.data1 || []
        //     data.forEach(link => {
        //         insertLinkImg(link)
        //     })
        // },
        before: function before(xhr, editor, files) {
            // 鍥剧墖涓婁紶涔嬪墠瑙﹀彂

            // 濡傛灉杩斿洖鐨勭粨鏋滄槸 {prevent: true, msg: 'xxxx'} 鍒欒〃绀虹敤鎴锋斁寮冧笂浼?            // return {
            //     prevent: true,
            //     msg: '鏀惧純涓婁紶'
            // }
        },
        success: function success(xhr, editor, result) {
            // 鍥剧墖涓婁紶骞惰繑鍥炵粨鏋滐紝鍥剧墖鎻掑叆鎴愬姛涔嬪悗瑙﹀彂
        },
        fail: function fail(xhr, editor, result) {
            // 鍥剧墖涓婁紶骞惰繑鍥炵粨鏋滐紝浣嗗浘鐗囨彃鍏ラ敊璇椂瑙﹀彂
        },
        error: function error(xhr, editor) {
            // 鍥剧墖涓婁紶鍑洪敊鏃惰Е鍙?        },
        timeout: function timeout(xhr, editor) {
            // 鍥剧墖涓婁紶瓒呮椂鏃惰Е鍙?        }
    },

    // 鏄惁涓婁紶涓冪墰浜戯紝榛樿涓?false
    qiniu: false

};

/*
    宸ュ叿
*/

// 鍜?UA 鐩稿叧鐨勫睘鎬?var UA = {
    _ua: navigator.userAgent,

    // 鏄惁 webkit
    isWebkit: function isWebkit() {
        var reg = /webkit/i;
        return reg.test(this._ua);
    },

    // 鏄惁 IE
    isIE: function isIE() {
        return 'ActiveXObject' in window;
    }
};

// 閬嶅巻瀵硅薄
function objForEach(obj, fn) {
    var key = void 0,
        result = void 0;
    for (key in obj) {
        if (obj.hasOwnProperty(key)) {
            result = fn.call(obj, key, obj[key]);
            if (result === false) {
                break;
            }
        }
    }
}

// 閬嶅巻绫绘暟缁?function arrForEach(fakeArr, fn) {
    var i = void 0,
        item = void 0,
        result = void 0;
    var length = fakeArr.length || 0;
    for (i = 0; i < length; i++) {
        item = fakeArr[i];
        result = fn.call(fakeArr, item, i);
        if (result === false) {
            break;
        }
    }
}

// 鑾峰彇闅忔満鏁?function getRandom(prefix) {
    return prefix + Math.random().toString().slice(2);
}

// 鏇挎崲 html 鐗规畩瀛楃
function replaceHtmlSymbol(html) {
    if (html == null) {
        return '';
    }
    return html.replace(/</gm, '&lt;').replace(/>/gm, '&gt;').replace(/"/gm, '&quot;').replace(/(\r\n|\r|\n)/g, '<br/>');
}

// 杩斿洖鐧惧垎姣旂殑鏍煎紡


// 鍒ゆ柇鏄笉鏄?function
function isFunction(fn) {
    return typeof fn === 'function';
}

/*
    bold-menu
*/
// 鏋勯€犲嚱鏁?function Bold(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-bold"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Bold.prototype = {
    constructor: Bold,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;
        var isSeleEmpty = editor.selection.isSelectionEmpty();

        if (isSeleEmpty) {
            // 閫夊尯鏄┖鐨勶紝鎻掑叆骞堕€変腑涓€涓€滅┖鐧解€?            editor.selection.createEmptyRange();
        }

        // 鎵ц bold 鍛戒护
        editor.cmd.do('bold');

        if (isSeleEmpty) {
            // 闇€瑕佸皢閫夊彇鎶樺彔璧锋潵
            editor.selection.collapseRange();
            editor.selection.restoreSelection();
        }
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor.cmd.queryCommandState('bold')) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    鏇挎崲澶氳瑷€
 */

var replaceLang = function (editor, str) {
    var langArgs = editor.config.langArgs || [];
    var result = str;

    langArgs.forEach(function (item) {
        var reg = item.reg;
        var val = item.val;

        if (reg.test(result)) {
            result = result.replace(reg, function () {
                return val;
            });
        }
    });

    return result;
};

/*
    droplist
*/
var _emptyFn = function _emptyFn() {};

// 鏋勯€犲嚱鏁?function DropList(menu, opt) {
    var _this = this;

    // droplist 鎵€渚濋檮鐨勮彍鍗?    var editor = menu.editor;
    this.menu = menu;
    this.opt = opt;
    // 瀹瑰櫒
    var $container = $('<div class="w-e-droplist"></div>');

    // 鏍囬
    var $title = opt.$title;
    var titleHtml = void 0;
    if ($title) {
        // 鏇挎崲澶氳瑷€
        titleHtml = $title.html();
        titleHtml = replaceLang(editor, titleHtml);
        $title.html(titleHtml);

        $title.addClass('w-e-dp-title');
        $container.append($title);
    }

    var list = opt.list || [];
    var type = opt.type || 'list'; // 'list' 鍒楄〃褰㈠紡锛堝鈥滄爣棰樷€濊彍鍗曪級 / 'inline-block' 鍧楃姸褰㈠紡锛堝鈥滈鑹测€濊彍鍗曪級
    var onClick = opt.onClick || _emptyFn;

    // 鍔犲叆 DOM 骞剁粦瀹氫簨浠?    var $list = $('<ul class="' + (type === 'list' ? 'w-e-list' : 'w-e-block') + '"></ul>');
    $container.append($list);
    list.forEach(function (item) {
        var $elem = item.$elem;

        // 鏇挎崲澶氳瑷€
        var elemHtml = $elem.html();
        elemHtml = replaceLang(editor, elemHtml);
        $elem.html(elemHtml);

        var value = item.value;
        var $li = $('<li class="w-e-item"></li>');
        if ($elem) {
            $li.append($elem);
            $list.append($li);
            $li.on('click', function (e) {
                onClick(value);

                // 闅愯棌
                _this.hideTimeoutId = setTimeout(function () {
                    _this.hide();
                }, 0);
            });
        }
    });

    // 缁戝畾闅愯棌浜嬩欢
    $container.on('mouseleave', function (e) {
        _this.hideTimeoutId = setTimeout(function () {
            _this.hide();
        }, 0);
    });

    // 璁板綍灞炴€?    this.$container = $container;

    // 鍩烘湰灞炴€?    this._rendered = false;
    this._show = false;
}

// 鍘熷瀷
DropList.prototype = {
    constructor: DropList,

    // 鏄剧ず锛堟彃鍏OM锛?    show: function show() {
        if (this.hideTimeoutId) {
            // 娓呴櫎涔嬪墠鐨勫畾鏃堕殣钘?            clearTimeout(this.hideTimeoutId);
        }

        var menu = this.menu;
        var $menuELem = menu.$elem;
        var $container = this.$container;
        if (this._show) {
            return;
        }
        if (this._rendered) {
            // 鏄剧ず
            $container.show();
        } else {
            // 鍔犲叆 DOM 涔嬪墠鍏堝畾浣嶄綅缃?            var menuHeight = $menuELem.getSizeData().height || 0;
            var width = this.opt.width || 100; // 榛樿涓?100
            $container.css('margin-top', menuHeight + 'px').css('width', width + 'px');

            // 鍔犲叆鍒?DOM
            $menuELem.append($container);
            this._rendered = true;
        }

        // 淇敼灞炴€?        this._show = true;
    },

    // 闅愯棌锛堢Щ闄OM锛?    hide: function hide() {
        if (this.showTimeoutId) {
            // 娓呴櫎涔嬪墠鐨勫畾鏃舵樉绀?            clearTimeout(this.showTimeoutId);
        }

        var $container = this.$container;
        if (!this._show) {
            return;
        }
        // 闅愯棌骞堕渶鏀瑰睘鎬?        $container.hide();
        this._show = false;
    }
};

/*
    menu - header
*/
// 鏋勯€犲嚱鏁?function Head(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-header"></i></div>');
    this.type = 'droplist';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 100,
        $title: $('<p>璁剧疆鏍囬</p>'),
        type: 'list', // droplist 浠ュ垪琛ㄥ舰寮忓睍绀?        list: [{ $elem: $('<h1>H1</h1>'), value: '<h1>' }, { $elem: $('<h2>H2</h2>'), value: '<h2>' }, { $elem: $('<h3>H3</h3>'), value: '<h3>' }, { $elem: $('<h4>H4</h4>'), value: '<h4>' }, { $elem: $('<h5>H5</h5>'), value: '<h5>' }, { $elem: $('<p>姝ｆ枃</p>'), value: '<p>' }],
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 Head 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
Head.prototype = {
    constructor: Head,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;

        var $selectionElem = editor.selection.getSelectionContainerElem();
        if (editor.$textElem.equal($selectionElem)) {
            // 涓嶈兘閫変腑澶氳鏉ヨ缃爣棰橈紝鍚﹀垯浼氬嚭鐜伴棶棰?            // 渚嬪閫変腑鐨勬槸 <p>xxx</p><p>yyy</p> 鏉ヨ缃爣棰橈紝璁剧疆涔嬪悗浼氭垚涓?<h1>xxx<br>yyy</h1> 涓嶇鍚堥鏈?            return;
        }

        editor.cmd.do('formatBlock', value);
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        var reg = /^h/i;
        var cmdValue = editor.cmd.queryCommandValue('formatBlock');
        if (reg.test(cmdValue)) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    menu - fontSize
*/

// 鏋勯€犲嚱鏁?function FontSize(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-text-heigh"></i></div>');
    this.type = 'droplist';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 160,
        $title: $('<p>瀛楀彿</p>'),
        type: 'list', // droplist 浠ュ垪琛ㄥ舰寮忓睍绀?        list: [{ $elem: $('<span style="font-size: x-small;">x-small</span>'), value: '1' }, { $elem: $('<span style="font-size: small;">small</span>'), value: '2' }, { $elem: $('<span>normal</span>'), value: '3' }, { $elem: $('<span style="font-size: large;">large</span>'), value: '4' }, { $elem: $('<span style="font-size: x-large;">x-large</span>'), value: '5' }, { $elem: $('<span style="font-size: xx-large;">xx-large</span>'), value: '6' }],
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 FontSize 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
FontSize.prototype = {
    constructor: FontSize,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;
        editor.cmd.do('fontSize', value);
    }
};

/*
    menu - fontName
*/

// 鏋勯€犲嚱鏁?function FontName(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-font"></i></div>');
    this.type = 'droplist';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鑾峰彇閰嶇疆鐨勫瓧浣?    var config = editor.config;
    var fontNames = config.fontNames || [];

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 100,
        $title: $('<p>瀛椾綋</p>'),
        type: 'list', // droplist 浠ュ垪琛ㄥ舰寮忓睍绀?        list: fontNames.map(function (fontName) {
            return { $elem: $('<span style="font-family: ' + fontName + ';">' + fontName + '</span>'), value: fontName };
        }),
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 FontName 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
FontName.prototype = {
    constructor: FontName,

    _command: function _command(value) {
        var editor = this.editor;
        editor.cmd.do('fontName', value);
    }
};

/*
    panel
*/

var emptyFn = function emptyFn() {};

// 璁板綍宸茬粡鏄剧ず panel 鐨勮彍鍗?var _isCreatedPanelMenus = [];

// 鏋勯€犲嚱鏁?function Panel(menu, opt) {
    this.menu = menu;
    this.opt = opt;
}

// 鍘熷瀷
Panel.prototype = {
    constructor: Panel,

    // 鏄剧ず锛堟彃鍏OM锛?    show: function show() {
        var _this = this;

        var menu = this.menu;
        if (_isCreatedPanelMenus.indexOf(menu) >= 0) {
            // 璇ヨ彍鍗曞凡缁忓垱寤轰簡 panel 涓嶈兘鍐嶅垱寤?            return;
        }

        var editor = menu.editor;
        var $body = $('body');
        var $textContainerElem = editor.$textContainerElem;
        var opt = this.opt;

        // panel 鐨勫鍣?        var $container = $('<div class="w-e-panel-container"></div>');
        var width = opt.width || 300; // 榛樿 300px
        $container.css('width', width + 'px').css('margin-left', (0 - width) / 2 + 'px');

        // 娣诲姞鍏抽棴鎸夐挳
        var $closeBtn = $('<i class="w-e-icon-close w-e-panel-close"></i>');
        $container.append($closeBtn);
        $closeBtn.on('click', function () {
            _this.hide();
        });

        // 鍑嗗 tabs 瀹瑰櫒
        var $tabTitleContainer = $('<ul class="w-e-panel-tab-title"></ul>');
        var $tabContentContainer = $('<div class="w-e-panel-tab-content"></div>');
        $container.append($tabTitleContainer).append($tabContentContainer);

        // 璁剧疆楂樺害
        var height = opt.height;
        if (height) {
            $tabContentContainer.css('height', height + 'px').css('overflow-y', 'auto');
        }

        // tabs
        var tabs = opt.tabs || [];
        var tabTitleArr = [];
        var tabContentArr = [];
        tabs.forEach(function (tab, tabIndex) {
            if (!tab) {
                return;
            }
            var title = tab.title || '';
            var tpl = tab.tpl || '';

            // 鏇挎崲澶氳瑷€
            title = replaceLang(editor, title);
            tpl = replaceLang(editor, tpl);

            // 娣诲姞鍒?DOM
            var $title = $('<li class="w-e-item">' + title + '</li>');
            $tabTitleContainer.append($title);
            var $content = $(tpl);
            $tabContentContainer.append($content);

            // 璁板綍鍒板唴瀛?            $title._index = tabIndex;
            tabTitleArr.push($title);
            tabContentArr.push($content);

            // 璁剧疆 active 椤?            if (tabIndex === 0) {
                $title._active = true;
                $title.addClass('w-e-active');
            } else {
                $content.hide();
            }

            // 缁戝畾 tab 鐨勪簨浠?            $title.on('click', function (e) {
                if ($title._active) {
                    return;
                }
                // 闅愯棌鎵€鏈夌殑 tab
                tabTitleArr.forEach(function ($title) {
                    $title._active = false;
                    $title.removeClass('w-e-active');
                });
                tabContentArr.forEach(function ($content) {
                    $content.hide();
                });

                // 鏄剧ず褰撳墠鐨?tab
                $title._active = true;
                $title.addClass('w-e-active');
                $content.show();
            });
        });

        // 缁戝畾鍏抽棴浜嬩欢
        $container.on('click', function (e) {
            // 鐐瑰嚮鏃堕樆姝㈠啋娉?            e.stopPropagation();
        });
        $body.on('click', function (e) {
            _this.hide();
        });

        // 娣诲姞鍒?DOM
        $textContainerElem.append($container);

        // 缁戝畾 opt 鐨勪簨浠讹紝鍙湁娣诲姞鍒?DOM 涔嬪悗鎵嶈兘缁戝畾鎴愬姛
        tabs.forEach(function (tab, index) {
            if (!tab) {
                return;
            }
            var events = tab.events || [];
            events.forEach(function (event) {
                var selector = event.selector;
                var type = event.type;
                var fn = event.fn || emptyFn;
                var $content = tabContentArr[index];
                $content.find(selector).on(type, function (e) {
                    e.stopPropagation();
                    var needToHide = fn(e);
                    // 鎵ц瀹屼簨浠朵箣鍚庯紝鏄惁瑕佸叧闂?panel
                    if (needToHide) {
                        _this.hide();
                    }
                });
            });
        });

        // focus 绗竴涓?elem
        var $inputs = $container.find('input[type=text],textarea');
        if ($inputs.length) {
            $inputs.get(0).focus();
        }

        // 娣诲姞鍒板睘鎬?        this.$container = $container;

        // 闅愯棌鍏朵粬 panel
        this._hideOtherPanels();
        // 璁板綍璇?menu 宸茬粡鍒涘缓浜?panel
        _isCreatedPanelMenus.push(menu);
    },

    // 闅愯棌锛堢Щ闄OM锛?    hide: function hide() {
        var menu = this.menu;
        var $container = this.$container;
        if ($container) {
            $container.remove();
        }

        // 灏嗚 menu 璁板綍涓Щ闄?        _isCreatedPanelMenus = _isCreatedPanelMenus.filter(function (item) {
            if (item === menu) {
                return false;
            } else {
                return true;
            }
        });
    },

    // 涓€涓?panel 灞曠ず鏃讹紝闅愯棌鍏朵粬 panel
    _hideOtherPanels: function _hideOtherPanels() {
        if (!_isCreatedPanelMenus.length) {
            return;
        }
        _isCreatedPanelMenus.forEach(function (menu) {
            var panel = menu.panel || {};
            if (panel.hide) {
                panel.hide();
            }
        });
    }
};

/*
    menu - link
*/
// 鏋勯€犲嚱鏁?function Link(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-link"></i></div>');
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Link.prototype = {
    constructor: Link,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        var editor = this.editor;
        var $linkelem = void 0;

        if (this._active) {
            // 褰撳墠閫夊尯鍦ㄩ摼鎺ラ噷闈?            $linkelem = editor.selection.getSelectionContainerElem();
            if (!$linkelem) {
                return;
            }
            // 灏嗚鍏冪礌閮藉寘鍚湪閫夊彇涔嬪唴锛屼互渚垮悗闈㈡暣浣撴浛鎹?            editor.selection.createRangeByElem($linkelem);
            editor.selection.restoreSelection();
            // 鏄剧ず panel
            this._createPanel($linkelem.text(), $linkelem.attr('href'));
        } else {
            // 褰撳墠閫夊尯涓嶅湪閾炬帴閲岄潰
            if (editor.selection.isSelectionEmpty()) {
                // 閫夊尯鏄┖鐨勶紝鏈€変腑鍐呭
                this._createPanel('', '');
            } else {
                // 閫変腑鍐呭浜?                this._createPanel(editor.selection.getSelectionText(), '');
            }
        }
    },

    // 鍒涘缓 panel
    _createPanel: function _createPanel(text, link) {
        var _this = this;

        // panel 涓渶瑕佺敤鍒扮殑id
        var inputLinkId = getRandom('input-link');
        var inputTextId = getRandom('input-text');
        var btnOkId = getRandom('btn-ok');
        var btnDelId = getRandom('btn-del');

        // 鏄惁鏄剧ず鈥滃垹闄ら摼鎺モ€?        var delBtnDisplay = this._active ? 'inline-block' : 'none';

        // 鍒濆鍖栧苟鏄剧ず panel
        var panel = new Panel(this, {
            width: 300,
            // panel 涓彲鍖呭惈澶氫釜 tab
            tabs: [{
                // tab 鐨勬爣棰?                title: '閾炬帴',
                // 妯℃澘
                tpl: '<div>\n                            <input id="' + inputTextId + '" type="text" class="block" value="' + text + '" placeholder="\u94FE\u63A5\u6587\u5B57"/></td>\n                            <input id="' + inputLinkId + '" type="text" class="block" value="' + link + '" placeholder="http://..."/></td>\n                            <div class="w-e-button-container">\n                                <button id="' + btnOkId + '" class="right">\u63D2\u5165</button>\n                                <button id="' + btnDelId + '" class="gray right" style="display:' + delBtnDisplay + '">\u5220\u9664\u94FE\u63A5</button>\n                            </div>\n                        </div>',
                // 浜嬩欢缁戝畾
                events: [
                // 鎻掑叆閾炬帴
                {
                    selector: '#' + btnOkId,
                    type: 'click',
                    fn: function fn() {
                        // 鎵ц鎻掑叆閾炬帴
                        var $link = $('#' + inputLinkId);
                        var $text = $('#' + inputTextId);
                        var link = $link.val();
                        var text = $text.val();
                        _this._insertLink(text, link);

                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                },
                // 鍒犻櫎閾炬帴
                {
                    selector: '#' + btnDelId,
                    type: 'click',
                    fn: function fn() {
                        // 鎵ц鍒犻櫎閾炬帴
                        _this._delLink();

                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            } // tab end
            ] // tabs end
        });

        // 鏄剧ず panel
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 鍒犻櫎褰撳墠閾炬帴
    _delLink: function _delLink() {
        if (!this._active) {
            return;
        }
        var editor = this.editor;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        var selectionText = editor.selection.getSelectionText();
        editor.cmd.do('insertHTML', '<span>' + selectionText + '</span>');
    },

    // 鎻掑叆閾炬帴
    _insertLink: function _insertLink(text, link) {
        var editor = this.editor;
        var config = editor.config;
        var linkCheck = config.linkCheck;
        var checkResult = true; // 榛樿涓?true
        if (linkCheck && typeof linkCheck === 'function') {
            checkResult = linkCheck(text, link);
        }
        if (checkResult === true) {
            editor.cmd.do('insertHTML', '<a href="' + link + '" target="_blank">' + text + '</a>');
        } else {
            alert(checkResult);
        }
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        if ($selectionELem.getNodeName() === 'A') {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    italic-menu
*/
// 鏋勯€犲嚱鏁?function Italic(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-italic"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Italic.prototype = {
    constructor: Italic,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;
        var isSeleEmpty = editor.selection.isSelectionEmpty();

        if (isSeleEmpty) {
            // 閫夊尯鏄┖鐨勶紝鎻掑叆骞堕€変腑涓€涓€滅┖鐧解€?            editor.selection.createEmptyRange();
        }

        // 鎵ц italic 鍛戒护
        editor.cmd.do('italic');

        if (isSeleEmpty) {
            // 闇€瑕佸皢閫夊彇鎶樺彔璧锋潵
            editor.selection.collapseRange();
            editor.selection.restoreSelection();
        }
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor.cmd.queryCommandState('italic')) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    redo-menu
*/
// 鏋勯€犲嚱鏁?function Redo(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-redo"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Redo.prototype = {
    constructor: Redo,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;

        // 鎵ц redo 鍛戒护
        editor.cmd.do('redo');
    }
};

/*
    strikeThrough-menu
*/
// 鏋勯€犲嚱鏁?function StrikeThrough(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-strikethrough"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
StrikeThrough.prototype = {
    constructor: StrikeThrough,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;
        var isSeleEmpty = editor.selection.isSelectionEmpty();

        if (isSeleEmpty) {
            // 閫夊尯鏄┖鐨勶紝鎻掑叆骞堕€変腑涓€涓€滅┖鐧解€?            editor.selection.createEmptyRange();
        }

        // 鎵ц strikeThrough 鍛戒护
        editor.cmd.do('strikeThrough');

        if (isSeleEmpty) {
            // 闇€瑕佸皢閫夊彇鎶樺彔璧锋潵
            editor.selection.collapseRange();
            editor.selection.restoreSelection();
        }
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor.cmd.queryCommandState('strikeThrough')) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    underline-menu
*/
// 鏋勯€犲嚱鏁?function Underline(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-underline"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Underline.prototype = {
    constructor: Underline,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;
        var isSeleEmpty = editor.selection.isSelectionEmpty();

        if (isSeleEmpty) {
            // 閫夊尯鏄┖鐨勶紝鎻掑叆骞堕€変腑涓€涓€滅┖鐧解€?            editor.selection.createEmptyRange();
        }

        // 鎵ц underline 鍛戒护
        editor.cmd.do('underline');

        if (isSeleEmpty) {
            // 闇€瑕佸皢閫夊彇鎶樺彔璧锋潵
            editor.selection.collapseRange();
            editor.selection.restoreSelection();
        }
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor.cmd.queryCommandState('underline')) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    undo-menu
*/
// 鏋勯€犲嚱鏁?function Undo(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-undo"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Undo.prototype = {
    constructor: Undo,

    // 鐐瑰嚮浜嬩欢
    onClick: function onClick(e) {
        // 鐐瑰嚮鑿滃崟灏嗚Е鍙戣繖閲?
        var editor = this.editor;

        // 鎵ц undo 鍛戒护
        editor.cmd.do('undo');
    }
};

/*
    menu - list
*/
// 鏋勯€犲嚱鏁?function List(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-list2"></i></div>');
    this.type = 'droplist';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 120,
        $title: $('<p>璁剧疆鍒楄〃</p>'),
        type: 'list', // droplist 浠ュ垪琛ㄥ舰寮忓睍绀?        list: [{ $elem: $('<span><i class="w-e-icon-list-numbered"></i> 鏈夊簭鍒楄〃</span>'), value: 'insertOrderedList' }, { $elem: $('<span><i class="w-e-icon-list2"></i> 鏃犲簭鍒楄〃</span>'), value: 'insertUnorderedList' }],
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 List 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
List.prototype = {
    constructor: List,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;
        var $textElem = editor.$textElem;
        editor.selection.restoreSelection();
        if (editor.cmd.queryCommandState(value)) {
            return;
        }
        editor.cmd.do(value);

        // 楠岃瘉鍒楄〃鏄惁琚寘瑁瑰湪 <p> 涔嬪唴
        var $selectionElem = editor.selection.getSelectionContainerElem();
        if ($selectionElem.getNodeName() === 'LI') {
            $selectionElem = $selectionElem.parent();
        }
        if (/^ol|ul$/i.test($selectionElem.getNodeName()) === false) {
            return;
        }
        if ($selectionElem.equal($textElem)) {
            // 璇佹槑鏄《绾ф爣绛撅紝娌℃湁琚?<p> 鍖呰９
            return;
        }
        var $parent = $selectionElem.parent();
        if ($parent.equal($textElem)) {
            // $parent 鏄《绾ф爣绛撅紝涓嶈兘鍒犻櫎
            return;
        }

        $selectionElem.insertAfter($parent);
        $parent.remove();
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor.cmd.queryCommandState('insertUnOrderedList') || editor.cmd.queryCommandState('insertOrderedList')) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    menu - justify
*/
// 鏋勯€犲嚱鏁?function Justify(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-paragraph-left"></i></div>');
    this.type = 'droplist';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 100,
        $title: $('<p>瀵归綈鏂瑰紡</p>'),
        type: 'list', // droplist 浠ュ垪琛ㄥ舰寮忓睍绀?        list: [{ $elem: $('<span><i class="w-e-icon-paragraph-left"></i> 闈犲乏</span>'), value: 'justifyLeft' }, { $elem: $('<span><i class="w-e-icon-paragraph-center"></i> 灞呬腑</span>'), value: 'justifyCenter' }, { $elem: $('<span><i class="w-e-icon-paragraph-right"></i> 闈犲彸</span>'), value: 'justifyRight' }],
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 List 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
Justify.prototype = {
    constructor: Justify,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;
        editor.cmd.do(value);
    }
};

/*
    menu - Forecolor
*/
// 鏋勯€犲嚱鏁?function ForeColor(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-pencil2"></i></div>');
    this.type = 'droplist';

    // 鑾峰彇閰嶇疆鐨勯鑹?    var config = editor.config;
    var colors = config.colors || [];

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 120,
        $title: $('<p>鏂囧瓧棰滆壊</p>'),
        type: 'inline-block', // droplist 鍐呭浠?block 褰㈠紡灞曠ず
        list: colors.map(function (color) {
            return { $elem: $('<i style="color:' + color + ';" class="w-e-icon-pencil2"></i>'), value: color };
        }),
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 ForeColor 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
ForeColor.prototype = {
    constructor: ForeColor,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;
        editor.cmd.do('foreColor', value);
    }
};

/*
    menu - BackColor
*/
// 鏋勯€犲嚱鏁?function BackColor(editor) {
    var _this = this;

    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-paint-brush"></i></div>');
    this.type = 'droplist';

    // 鑾峰彇閰嶇疆鐨勯鑹?    var config = editor.config;
    var colors = config.colors || [];

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;

    // 鍒濆鍖?droplist
    this.droplist = new DropList(this, {
        width: 120,
        $title: $('<p>鑳屾櫙鑹?/p>'),
        type: 'inline-block', // droplist 鍐呭浠?block 褰㈠紡灞曠ず
        list: colors.map(function (color) {
            return { $elem: $('<i style="color:' + color + ';" class="w-e-icon-paint-brush"></i>'), value: color };
        }),
        onClick: function onClick(value) {
            // 娉ㄦ剰 this 鏄寚鍚戝綋鍓嶇殑 BackColor 瀵硅薄
            _this._command(value);
        }
    });
}

// 鍘熷瀷
BackColor.prototype = {
    constructor: BackColor,

    // 鎵ц鍛戒护
    _command: function _command(value) {
        var editor = this.editor;
        editor.cmd.do('backColor', value);
    }
};

/*
    menu - quote
*/
// 鏋勯€犲嚱鏁?function Quote(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-quotes-left"></i>\n        </div>');
    this.type = 'click';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Quote.prototype = {
    constructor: Quote,

    onClick: function onClick(e) {
        var editor = this.editor;
        var $selectionElem = editor.selection.getSelectionContainerElem();
        var nodeName = $selectionElem.getNodeName();

        if (!UA.isIE()) {
            if (nodeName === 'BLOCKQUOTE') {
                // 鎾ら攢 quote
                editor.cmd.do('formatBlock', '<P>');
            } else {
                // 杞崲涓?quote
                editor.cmd.do('formatBlock', '<BLOCKQUOTE>');
            }
            return;
        }

        // IE 涓笉鏀寔 formatBlock <BLOCKQUOTE> 锛岃鐢ㄥ叾浠栨柟寮忓吋瀹?        var content = void 0,
            $targetELem = void 0;
        if (nodeName === 'P') {
            // 灏?P 杞崲涓?quote
            content = $selectionElem.text();
            $targetELem = $('<blockquote>' + content + '</blockquote>');
            $targetELem.insertAfter($selectionElem);
            $selectionElem.remove();
            return;
        }
        if (nodeName === 'BLOCKQUOTE') {
            // 鎾ら攢 quote
            content = $selectionElem.text();
            $targetELem = $('<p>' + content + '</p>');
            $targetELem.insertAfter($selectionElem);
            $selectionElem.remove();
        }
    },

    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        var reg = /^BLOCKQUOTE$/i;
        var cmdValue = editor.cmd.queryCommandValue('formatBlock');
        if (reg.test(cmdValue)) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    menu - code
*/
// 鏋勯€犲嚱鏁?function Code(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-terminal"></i>\n        </div>');
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Code.prototype = {
    constructor: Code,

    onClick: function onClick(e) {
        var editor = this.editor;
        var $startElem = editor.selection.getSelectionStartElem();
        var $endElem = editor.selection.getSelectionEndElem();
        var isSeleEmpty = editor.selection.isSelectionEmpty();
        var selectionText = editor.selection.getSelectionText();
        var $code = void 0;

        if (!$startElem.equal($endElem)) {
            // 璺ㄥ厓绱犻€夋嫨锛屼笉鍋氬鐞?            editor.selection.restoreSelection();
            return;
        }
        if (!isSeleEmpty) {
            // 閫夊彇涓嶆槸绌猴紝鐢?<code> 鍖呰９鍗冲彲
            $code = $('<code>' + selectionText + '</code>');
            editor.cmd.do('insertElem', $code);
            editor.selection.createRangeByElem($code, false);
            editor.selection.restoreSelection();
            return;
        }

        // 閫夊彇鏄┖锛屼笖娌℃湁澶稿厓绱犻€夋嫨锛屽垯鎻掑叆 <pre><code></code></prev>
        if (this._active) {
            // 閫変腑鐘舵€侊紝灏嗙紪杈戝唴瀹?            this._createPanel($startElem.html());
        } else {
            // 鏈€変腑鐘舵€侊紝灏嗗垱寤哄唴瀹?            this._createPanel();
        }
    },

    _createPanel: function _createPanel(value) {
        var _this = this;

        // value - 瑕佺紪杈戠殑鍐呭
        value = value || '';
        var type = !value ? 'new' : 'edit';
        var textId = getRandom('texxt');
        var btnId = getRandom('btn');

        var panel = new Panel(this, {
            width: 500,
            // 涓€涓?Panel 鍖呭惈澶氫釜 tab
            tabs: [{
                // 鏍囬
                title: '鎻掑叆浠ｇ爜',
                // 妯℃澘
                tpl: '<div>\n                        <textarea id="' + textId + '" style="height:145px;;">' + value + '</textarea>\n                        <div class="w-e-button-container">\n                            <button id="' + btnId + '" class="right">\u63D2\u5165</button>\n                        </div>\n                    <div>',
                // 浜嬩欢缁戝畾
                events: [
                // 鎻掑叆浠ｇ爜
                {
                    selector: '#' + btnId,
                    type: 'click',
                    fn: function fn() {
                        var $text = $('#' + textId);
                        var text = $text.val() || $text.html();
                        text = replaceHtmlSymbol(text);
                        if (type === 'new') {
                            // 鏂版彃鍏?                            _this._insertCode(text);
                        } else {
                            // 缂栬緫鏇存柊
                            _this._updateCode(text);
                        }

                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            } // first tab end
            ] // tabs end
        }); // new Panel end

        // 鏄剧ず panel
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 鎻掑叆浠ｇ爜
    _insertCode: function _insertCode(value) {
        var editor = this.editor;
        editor.cmd.do('insertHTML', '<pre><code>' + value + '</code></pre><p><br></p>');
    },

    // 鏇存柊浠ｇ爜
    _updateCode: function _updateCode(value) {
        var editor = this.editor;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        $selectionELem.html(value);
        editor.selection.restoreSelection();
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        var $parentElem = $selectionELem.parent();
        if ($selectionELem.getNodeName() === 'CODE' && $parentElem.getNodeName() === 'PRE') {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    menu - emoticon
*/
// 鏋勯€犲嚱鏁?function Emoticon(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu">\n            <i class="w-e-icon-happy"></i>\n        </div>');
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Emoticon.prototype = {
    constructor: Emoticon,

    onClick: function onClick() {
        this._createPanel();
    },

    _createPanel: function _createPanel() {
        var _this = this;

        var editor = this.editor;
        var config = editor.config;
        // 鑾峰彇琛ㄦ儏閰嶇疆
        var emotions = config.emotions || [];

        // 鍒涘缓琛ㄦ儏 dropPanel 鐨勯厤缃?        var tabConfig = [];
        emotions.forEach(function (emotData) {
            var emotType = emotData.type;
            var content = emotData.content || [];

            // 杩欎竴缁勮〃鎯呮渶缁堟嫾鎺ュ嚭鏉ョ殑 html
            var faceHtml = '';

            // emoji 琛ㄦ儏
            if (emotType === 'emoji') {
                content.forEach(function (item) {
                    if (item) {
                        faceHtml += '<span class="w-e-item">' + item + '</span>';
                    }
                });
            }
            // 鍥剧墖琛ㄦ儏
            if (emotType === 'image') {
                content.forEach(function (item) {
                    var src = item.src;
                    var alt = item.alt;
                    if (src) {
                        // 鍔犱竴涓?data-w-e 灞炴€э紝鐐瑰嚮鍥剧墖鐨勬椂鍊欎笉鍐嶆彁绀虹紪杈戝浘鐗?                        faceHtml += '<span class="w-e-item"><img src="' + src + '" alt="' + alt + '" data-w-e="1"/></span>';
                    }
                });
            }

            tabConfig.push({
                title: emotData.title,
                tpl: '<div class="w-e-emoticon-container">' + faceHtml + '</div>',
                events: [{
                    selector: 'span.w-e-item',
                    type: 'click',
                    fn: function fn(e) {
                        var target = e.target;
                        var $target = $(target);
                        var nodeName = $target.getNodeName();

                        var insertHtml = void 0;
                        if (nodeName === 'IMG') {
                            // 鎻掑叆鍥剧墖
                            insertHtml = $target.parent().html();
                        } else {
                            // 鎻掑叆 emoji
                            insertHtml = '<span>' + $target.html() + '</span>';
                        }

                        _this._insert(insertHtml);
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            });
        });

        var panel = new Panel(this, {
            width: 300,
            height: 200,
            // 涓€涓?Panel 鍖呭惈澶氫釜 tab
            tabs: tabConfig
        });

        // 鏄剧ず panel
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 鎻掑叆琛ㄦ儏
    _insert: function _insert(emotHtml) {
        var editor = this.editor;
        editor.cmd.do('insertHTML', emotHtml);
    }
};

/*
    menu - table
*/
// 鏋勯€犲嚱鏁?function Table(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-table2"></i></div>');
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Table.prototype = {
    constructor: Table,

    onClick: function onClick() {
        if (this._active) {
            // 缂栬緫鐜版湁琛ㄦ牸
            this._createEditPanel();
        } else {
            // 鎻掑叆鏂拌〃鏍?            this._createInsertPanel();
        }
    },

    // 鍒涘缓鎻掑叆鏂拌〃鏍肩殑 panel
    _createInsertPanel: function _createInsertPanel() {
        var _this = this;

        // 鐢ㄥ埌鐨?id
        var btnInsertId = getRandom('btn');
        var textRowNum = getRandom('row');
        var textColNum = getRandom('col');

        var panel = new Panel(this, {
            width: 250,
            // panel 鍖呭惈澶氫釜 tab
            tabs: [{
                // 鏍囬
                title: '鎻掑叆琛ㄦ牸',
                // 妯℃澘
                tpl: '<div>\n                        <p style="text-align:left; padding:5px 0;">\n                            \u521B\u5EFA\n                            <input id="' + textRowNum + '" type="text" value="5" style="width:40px;text-align:center;"/>\n                            \u884C\n                            <input id="' + textColNum + '" type="text" value="5" style="width:40px;text-align:center;"/>\n                            \u5217\u7684\u8868\u683C\n                        </p>\n                        <div class="w-e-button-container">\n                            <button id="' + btnInsertId + '" class="right">\u63D2\u5165</button>\n                        </div>\n                    </div>',
                // 浜嬩欢缁戝畾
                events: [{
                    // 鐐瑰嚮鎸夐挳锛屾彃鍏ヨ〃鏍?                    selector: '#' + btnInsertId,
                    type: 'click',
                    fn: function fn() {
                        var rowNum = parseInt($('#' + textRowNum).val());
                        var colNum = parseInt($('#' + textColNum).val());

                        if (rowNum && colNum && rowNum > 0 && colNum > 0) {
                            // form 鏁版嵁鏈夋晥
                            _this._insert(rowNum, colNum);
                        }

                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            } // first tab end
            ] // tabs end
        }); // panel end

        // 灞曠ず panel
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 鎻掑叆琛ㄦ牸
    _insert: function _insert(rowNum, colNum) {
        // 鎷兼帴 table 妯℃澘
        var r = void 0,
            c = void 0;
        var html = '<table border="0" width="100%" cellpadding="0" cellspacing="0">';
        for (r = 0; r < rowNum; r++) {
            html += '<tr>';
            if (r === 0) {
                for (c = 0; c < colNum; c++) {
                    html += '<th>&nbsp;</th>';
                }
            } else {
                for (c = 0; c < colNum; c++) {
                    html += '<td>&nbsp;</td>';
                }
            }
            html += '</tr>';
        }
        html += '</table><p><br></p>';

        // 鎵ц鍛戒护
        var editor = this.editor;
        editor.cmd.do('insertHTML', html);

        // 闃叉 firefox 涓嬪嚭鐜?resize 鐨勬帶鍒剁偣
        editor.cmd.do('enableObjectResizing', false);
        editor.cmd.do('enableInlineTableEditing', false);
    },

    // 鍒涘缓缂栬緫琛ㄦ牸鐨?panel
    _createEditPanel: function _createEditPanel() {
        var _this2 = this;

        // 鍙敤鐨?id
        var addRowBtnId = getRandom('add-row');
        var addColBtnId = getRandom('add-col');
        var delRowBtnId = getRandom('del-row');
        var delColBtnId = getRandom('del-col');
        var delTableBtnId = getRandom('del-table');

        // 鍒涘缓 panel 瀵硅薄
        var panel = new Panel(this, {
            width: 320,
            // panel 鍖呭惈澶氫釜 tab
            tabs: [{
                // 鏍囬
                title: '缂栬緫琛ㄦ牸',
                // 妯℃澘
                tpl: '<div>\n                        <div class="w-e-button-container" style="border-bottom:1px solid #f1f1f1;padding-bottom:5px;margin-bottom:5px;">\n                            <button id="' + addRowBtnId + '" class="left">\u589E\u52A0\u884C</button>\n                            <button id="' + delRowBtnId + '" class="red left">\u5220\u9664\u884C</button>\n                            <button id="' + addColBtnId + '" class="left">\u589E\u52A0\u5217</button>\n                            <button id="' + delColBtnId + '" class="red left">\u5220\u9664\u5217</button>\n                        </div>\n                        <div class="w-e-button-container">\n                            <button id="' + delTableBtnId + '" class="gray left">\u5220\u9664\u8868\u683C</button>\n                        </dv>\n                    </div>',
                // 浜嬩欢缁戝畾
                events: [{
                    // 澧炲姞琛?                    selector: '#' + addRowBtnId,
                    type: 'click',
                    fn: function fn() {
                        _this2._addRow();
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }, {
                    // 澧炲姞鍒?                    selector: '#' + addColBtnId,
                    type: 'click',
                    fn: function fn() {
                        _this2._addCol();
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }, {
                    // 鍒犻櫎琛?                    selector: '#' + delRowBtnId,
                    type: 'click',
                    fn: function fn() {
                        _this2._delRow();
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }, {
                    // 鍒犻櫎鍒?                    selector: '#' + delColBtnId,
                    type: 'click',
                    fn: function fn() {
                        _this2._delCol();
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }, {
                    // 鍒犻櫎琛ㄦ牸
                    selector: '#' + delTableBtnId,
                    type: 'click',
                    fn: function fn() {
                        _this2._delTable();
                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            }]
        });
        // 鏄剧ず panel
        panel.show();
    },

    // 鑾峰彇閫変腑鐨勫崟鍏冩牸鐨勪綅缃俊鎭?    _getLocationData: function _getLocationData() {
        var result = {};
        var editor = this.editor;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        var nodeName = $selectionELem.getNodeName();
        if (nodeName !== 'TD' && nodeName !== 'TH') {
            return;
        }

        // 鑾峰彇 td index
        var $tr = $selectionELem.parent();
        var $tds = $tr.children();
        var tdLength = $tds.length;
        $tds.forEach(function (td, index) {
            if (td === $selectionELem[0]) {
                // 璁板綍骞惰烦鍑哄惊鐜?                result.td = {
                    index: index,
                    elem: td,
                    length: tdLength
                };
                return false;
            }
        });

        // 鑾峰彇 tr index
        var $tbody = $tr.parent();
        var $trs = $tbody.children();
        var trLength = $trs.length;
        $trs.forEach(function (tr, index) {
            if (tr === $tr[0]) {
                // 璁板綍骞惰烦鍑哄惊鐜?                result.tr = {
                    index: index,
                    elem: tr,
                    length: trLength
                };
                return false;
            }
        });

        // 杩斿洖缁撴灉
        return result;
    },

    // 澧炲姞琛?    _addRow: function _addRow() {
        // 鑾峰彇褰撳墠鍗曞厓鏍肩殑浣嶇疆淇℃伅
        var locationData = this._getLocationData();
        if (!locationData) {
            return;
        }
        var trData = locationData.tr;
        var $currentTr = $(trData.elem);
        var tdData = locationData.td;
        var tdLength = tdData.length;

        // 鎷兼帴鍗冲皢鎻掑叆鐨勫瓧绗︿覆
        var newTr = document.createElement('tr');
        var tpl = '',
            i = void 0;
        for (i = 0; i < tdLength; i++) {
            tpl += '<td>&nbsp;</td>';
        }
        newTr.innerHTML = tpl;
        // 鎻掑叆
        $(newTr).insertAfter($currentTr);
    },

    // 澧炲姞鍒?    _addCol: function _addCol() {
        // 鑾峰彇褰撳墠鍗曞厓鏍肩殑浣嶇疆淇℃伅
        var locationData = this._getLocationData();
        if (!locationData) {
            return;
        }
        var trData = locationData.tr;
        var tdData = locationData.td;
        var tdIndex = tdData.index;
        var $currentTr = $(trData.elem);
        var $trParent = $currentTr.parent();
        var $trs = $trParent.children();

        // 閬嶅巻鎵€鏈夎
        $trs.forEach(function (tr) {
            var $tr = $(tr);
            var $tds = $tr.children();
            var $currentTd = $tds.get(tdIndex);
            var name = $currentTd.getNodeName().toLowerCase();

            // new 涓€涓?td锛屽苟鎻掑叆
            var newTd = document.createElement(name);
            $(newTd).insertAfter($currentTd);
        });
    },

    // 鍒犻櫎琛?    _delRow: function _delRow() {
        // 鑾峰彇褰撳墠鍗曞厓鏍肩殑浣嶇疆淇℃伅
        var locationData = this._getLocationData();
        if (!locationData) {
            return;
        }
        var trData = locationData.tr;
        var $currentTr = $(trData.elem);
        $currentTr.remove();
    },

    // 鍒犻櫎鍒?    _delCol: function _delCol() {
        // 鑾峰彇褰撳墠鍗曞厓鏍肩殑浣嶇疆淇℃伅
        var locationData = this._getLocationData();
        if (!locationData) {
            return;
        }
        var trData = locationData.tr;
        var tdData = locationData.td;
        var tdIndex = tdData.index;
        var $currentTr = $(trData.elem);
        var $trParent = $currentTr.parent();
        var $trs = $trParent.children();

        // 閬嶅巻鎵€鏈夎
        $trs.forEach(function (tr) {
            var $tr = $(tr);
            var $tds = $tr.children();
            var $currentTd = $tds.get(tdIndex);
            // 鍒犻櫎
            $currentTd.remove();
        });
    },

    // 鍒犻櫎琛ㄦ牸
    _delTable: function _delTable() {
        var editor = this.editor;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        var $table = $selectionELem.parentUntil('table');
        if (!$table) {
            return;
        }
        $table.remove();
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        var $selectionELem = editor.selection.getSelectionContainerElem();
        if (!$selectionELem) {
            return;
        }
        var nodeName = $selectionELem.getNodeName();
        if (nodeName === 'TD' || nodeName === 'TH') {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    menu - video
*/
// 鏋勯€犲嚱鏁?function Video(editor) {
    this.editor = editor;
    this.$elem = $('<div class="w-e-menu"><i class="w-e-icon-play"></i></div>');
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Video.prototype = {
    constructor: Video,

    onClick: function onClick() {
        this._createPanel();
    },

    _createPanel: function _createPanel() {
        var _this = this;

        // 鍒涘缓 id
        var textValId = getRandom('text-val');
        var btnId = getRandom('btn');

        // 鍒涘缓 panel
        var panel = new Panel(this, {
            width: 350,
            // 涓€涓?panel 澶氫釜 tab
            tabs: [{
                // 鏍囬
                title: '鎻掑叆瑙嗛',
                // 妯℃澘
                tpl: '<div>\n                        <input id="' + textValId + '" type="text" class="block" placeholder="\u683C\u5F0F\u5982\uFF1A<iframe src=... ></iframe>"/>\n                        <div class="w-e-button-container">\n                            <button id="' + btnId + '" class="right">\u63D2\u5165</button>\n                        </div>\n                    </div>',
                // 浜嬩欢缁戝畾
                events: [{
                    selector: '#' + btnId,
                    type: 'click',
                    fn: function fn() {
                        var $text = $('#' + textValId);
                        var val = $text.val().trim();

                        // 娴嬭瘯鐢ㄨ棰戝湴鍧€
                        // <iframe height=498 width=510 src='http://player.youku.com/embed/XMjcwMzc3MzM3Mg==' frameborder=0 'allowfullscreen'></iframe>

                        if (val) {
                            // 鎻掑叆瑙嗛
                            _this._insert(val);
                        }

                        // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                        return true;
                    }
                }]
            } // first tab end
            ] // tabs end
        }); // panel end

        // 鏄剧ず panel
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 鎻掑叆瑙嗛
    _insert: function _insert(val) {
        var editor = this.editor;
        editor.cmd.do('insertHTML', val + '<p><br></p>');
    }
};

/*
    menu - img
*/
// 鏋勯€犲嚱鏁?function Image(editor) {
    this.editor = editor;
    var imgMenuId = getRandom('w-e-img');
    this.$elem = $('<div class="w-e-menu" id="' + imgMenuId + '"><i class="w-e-icon-image"></i></div>');
    editor.imgMenuId = imgMenuId;
    this.type = 'panel';

    // 褰撳墠鏄惁 active 鐘舵€?    this._active = false;
}

// 鍘熷瀷
Image.prototype = {
    constructor: Image,

    onClick: function onClick() {
        var editor = this.editor;
        var config = editor.config;
        if (config.qiniu) {
            return;
        }
        if (this._active) {
            this._createEditPanel();
        } else {
            this._createInsertPanel();
        }
    },

    _createEditPanel: function _createEditPanel() {
        var editor = this.editor;

        // id
        var width30 = getRandom('width-30');
        var width50 = getRandom('width-50');
        var width100 = getRandom('width-100');
        var delBtn = getRandom('del-btn');

        // tab 閰嶇疆
        var tabsConfig = [{
            title: '缂栬緫鍥剧墖',
            tpl: '<div>\n                    <div class="w-e-button-container" style="border-bottom:1px solid #f1f1f1;padding-bottom:5px;margin-bottom:5px;">\n                        <span style="float:left;font-size:14px;margin:4px 5px 0 5px;color:#333;">\u6700\u5927\u5BBD\u5EA6\uFF1A</span>\n                        <button id="' + width30 + '" class="left">30%</button>\n                        <button id="' + width50 + '" class="left">50%</button>\n                        <button id="' + width100 + '" class="left">100%</button>\n                    </div>\n                    <div class="w-e-button-container">\n                        <button id="' + delBtn + '" class="gray left">\u5220\u9664\u56FE\u7247</button>\n                    </dv>\n                </div>',
            events: [{
                selector: '#' + width30,
                type: 'click',
                fn: function fn() {
                    var $img = editor._selectedImg;
                    if ($img) {
                        $img.css('max-width', '30%');
                    }
                    // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                    return true;
                }
            }, {
                selector: '#' + width50,
                type: 'click',
                fn: function fn() {
                    var $img = editor._selectedImg;
                    if ($img) {
                        $img.css('max-width', '50%');
                    }
                    // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                    return true;
                }
            }, {
                selector: '#' + width100,
                type: 'click',
                fn: function fn() {
                    var $img = editor._selectedImg;
                    if ($img) {
                        $img.css('max-width', '100%');
                    }
                    // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                    return true;
                }
            }, {
                selector: '#' + delBtn,
                type: 'click',
                fn: function fn() {
                    var $img = editor._selectedImg;
                    if ($img) {
                        $img.remove();
                    }
                    // 杩斿洖 true锛岃〃绀鸿浜嬩欢鎵ц瀹屼箣鍚庯紝panel 瑕佸叧闂€傚惁鍒?panel 涓嶄細鍏抽棴
                    return true;
                }
            }]
        }];

        // 鍒涘缓 panel 骞舵樉绀?        var panel = new Panel(this, {
            width: 300,
            tabs: tabsConfig
        });
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    _createInsertPanel: function _createInsertPanel() {
        var editor = this.editor;
        var uploadImg = editor.uploadImg;
        var config = editor.config;

        // id
        var upTriggerId = getRandom('up-trigger');
        var upFileId = getRandom('up-file');
        var linkUrlId = getRandom('link-url');
        var linkBtnId = getRandom('link-btn');

        // tabs 鐨勯厤缃?        var tabsConfig = [{
            title: '涓婁紶鍥剧墖',
            tpl: '<div class="w-e-up-img-container">\n                    <div id="' + upTriggerId + '" class="w-e-up-btn">\n                        <i class="w-e-icon-upload2"></i>\n                    </div>\n                    <div style="display:none;">\n                        <input id="' + upFileId + '" type="file" multiple="multiple" accept="image/jpg,image/jpeg,image/png,image/gif,image/bmp"/>\n                    </div>\n                </div>',
            events: [{
                // 瑙﹀彂閫夋嫨鍥剧墖
                selector: '#' + upTriggerId,
                type: 'click',
                fn: function fn() {
                    var $file = $('#' + upFileId);
                    var fileElem = $file[0];
                    if (fileElem) {
                        fileElem.click();
                    } else {
                        // 杩斿洖 true 鍙叧闂?panel
                        return true;
                    }
                }
            }, {
                // 閫夋嫨鍥剧墖瀹屾瘯
                selector: '#' + upFileId,
                type: 'change',
                fn: function fn() {
                    var $file = $('#' + upFileId);
                    var fileElem = $file[0];
                    if (!fileElem) {
                        // 杩斿洖 true 鍙叧闂?panel
                        return true;
                    }

                    // 鑾峰彇閫変腑鐨?file 瀵硅薄鍒楄〃
                    var fileList = fileElem.files;
                    if (fileList.length) {
                        uploadImg.uploadImg(fileList);
                    }

                    // 杩斿洖 true 鍙叧闂?panel
                    return true;
                }
            }]
        }, // first tab end
        {
            title: '缃戠粶鍥剧墖',
            tpl: '<div>\n                    <input id="' + linkUrlId + '" type="text" class="block" placeholder="\u56FE\u7247\u94FE\u63A5"/></td>\n                    <div class="w-e-button-container">\n                        <button id="' + linkBtnId + '" class="right">\u63D2\u5165</button>\n                    </div>\n                </div>',
            events: [{
                selector: '#' + linkBtnId,
                type: 'click',
                fn: function fn() {
                    var $linkUrl = $('#' + linkUrlId);
                    var url = $linkUrl.val().trim();

                    if (url) {
                        uploadImg.insertLinkImg(url);
                    }

                    // 杩斿洖 true 琛ㄧず鍑芥暟鎵ц缁撴潫涔嬪悗鍏抽棴 panel
                    return true;
                }
            }]
        } // second tab end
        ]; // tabs end

        // 鍒ゆ柇 tabs 鐨勬樉绀?        var tabsConfigResult = [];
        if ((config.uploadImgShowBase64 || config.uploadImgServer || config.customUploadImg) && window.FileReader) {
            // 鏄剧ず鈥滀笂浼犲浘鐗団€?            tabsConfigResult.push(tabsConfig[0]);
        }
        if (config.showLinkImg) {
            // 鏄剧ず鈥滅綉缁滃浘鐗団€?            tabsConfigResult.push(tabsConfig[1]);
        }

        // 鍒涘缓 panel 骞舵樉绀?        var panel = new Panel(this, {
            width: 300,
            tabs: tabsConfigResult
        });
        panel.show();

        // 璁板綍灞炴€?        this.panel = panel;
    },

    // 璇曞浘鏀瑰彉 active 鐘舵€?    tryChangeActive: function tryChangeActive(e) {
        var editor = this.editor;
        var $elem = this.$elem;
        if (editor._selectedImg) {
            this._active = true;
            $elem.addClass('w-e-active');
        } else {
            this._active = false;
            $elem.removeClass('w-e-active');
        }
    }
};

/*
    鎵€鏈夎彍鍗曠殑姹囨€?*/

// 瀛樺偍鑿滃崟鐨勬瀯閫犲嚱鏁?var MenuConstructors = {};

MenuConstructors.bold = Bold;

MenuConstructors.head = Head;

MenuConstructors.fontSize = FontSize;

MenuConstructors.fontName = FontName;

MenuConstructors.link = Link;

MenuConstructors.italic = Italic;

MenuConstructors.redo = Redo;

MenuConstructors.strikeThrough = StrikeThrough;

MenuConstructors.underline = Underline;

MenuConstructors.undo = Undo;

MenuConstructors.list = List;

MenuConstructors.justify = Justify;

MenuConstructors.foreColor = ForeColor;

MenuConstructors.backColor = BackColor;

MenuConstructors.quote = Quote;

MenuConstructors.code = Code;

MenuConstructors.emoticon = Emoticon;

MenuConstructors.table = Table;

MenuConstructors.video = Video;

MenuConstructors.image = Image;

/*
    鑿滃崟闆嗗悎
*/
// 鏋勯€犲嚱鏁?function Menus(editor) {
    this.editor = editor;
    this.menus = {};
}

// 淇敼鍘熷瀷
Menus.prototype = {
    constructor: Menus,

    // 鍒濆鍖栬彍鍗?    init: function init() {
        var _this = this;

        var editor = this.editor;
        var config = editor.config || {};
        var configMenus = config.menus || []; // 鑾峰彇閰嶇疆涓殑鑿滃崟

        // 鏍规嵁閰嶇疆淇℃伅锛屽垱寤鸿彍鍗?        configMenus.forEach(function (menuKey) {
            var MenuConstructor = MenuConstructors[menuKey];
            if (MenuConstructor && typeof MenuConstructor === 'function') {
                // 鍒涘缓鍗曚釜鑿滃崟
                _this.menus[menuKey] = new MenuConstructor(editor);
            }
        });

        // 娣诲姞鍒拌彍鍗曟爮
        this._addToToolbar();

        // 缁戝畾浜嬩欢
        this._bindEvent();
    },

    // 娣诲姞鍒拌彍鍗曟爮
    _addToToolbar: function _addToToolbar() {
        var editor = this.editor;
        var $toolbarElem = editor.$toolbarElem;
        var menus = this.menus;
        var config = editor.config;
        // config.zIndex 鏄厤缃殑缂栬緫鍖哄煙鐨?z-index锛岃彍鍗曠殑 z-index 寰楀湪鍏跺熀纭€涓?+1
        var zIndex = config.zIndex + 1;
        objForEach(menus, function (key, menu) {
            var $elem = menu.$elem;
            if ($elem) {
                // 璁剧疆 z-index
                $elem.css('z-index', zIndex);
                $toolbarElem.append($elem);
            }
        });
    },

    // 缁戝畾鑿滃崟 click mouseenter 浜嬩欢
    _bindEvent: function _bindEvent() {
        var menus = this.menus;
        var editor = this.editor;
        objForEach(menus, function (key, menu) {
            var type = menu.type;
            if (!type) {
                return;
            }
            var $elem = menu.$elem;
            var droplist = menu.droplist;
            var panel = menu.panel;

            // 鐐瑰嚮绫诲瀷锛屼緥濡?bold
            if (type === 'click' && menu.onClick) {
                $elem.on('click', function (e) {
                    if (editor.selection.getRange() == null) {
                        return;
                    }
                    menu.onClick(e);
                });
            }

            // 涓嬫媺妗嗭紝渚嬪 head
            if (type === 'droplist' && droplist) {
                $elem.on('mouseenter', function (e) {
                    if (editor.selection.getRange() == null) {
                        return;
                    }
                    // 鏄剧ず
                    droplist.showTimeoutId = setTimeout(function () {
                        droplist.show();
                    }, 200);
                }).on('mouseleave', function (e) {
                    // 闅愯棌
                    droplist.hideTimeoutId = setTimeout(function () {
                        droplist.hide();
                    }, 0);
                });
            }

            // 寮规绫诲瀷锛屼緥濡?link
            if (type === 'panel' && menu.onClick) {
                $elem.on('click', function (e) {
                    e.stopPropagation();
                    if (editor.selection.getRange() == null) {
                        return;
                    }
                    // 鍦ㄨ嚜瀹氫箟浜嬩欢涓樉绀?panel
                    menu.onClick(e);
                });
            }
        });
    },

    // 灏濊瘯淇敼鑿滃崟鐘舵€?    changeActive: function changeActive() {
        var menus = this.menus;
        objForEach(menus, function (key, menu) {
            if (menu.tryChangeActive) {
                setTimeout(function () {
                    menu.tryChangeActive();
                }, 100);
            }
        });
    }
};

/*
    绮樿创淇℃伅鐨勫鐞?*/

// 鑾峰彇绮樿创鐨勭函鏂囨湰
function getPasteText(e) {
    var clipboardData = e.clipboardData || e.originalEvent && e.originalEvent.clipboardData;
    var pasteText = void 0;
    if (clipboardData == null) {
        pasteText = window.clipboardData && window.clipboardData.getData('text');
    } else {
        pasteText = clipboardData.getData('text/plain');
    }

    return replaceHtmlSymbol(pasteText);
}

// 鑾峰彇绮樿创鐨刪tml
function getPasteHtml(e, filterStyle, ignoreImg) {
    var clipboardData = e.clipboardData || e.originalEvent && e.originalEvent.clipboardData;
    var pasteText = void 0,
        pasteHtml = void 0;
    if (clipboardData == null) {
        pasteText = window.clipboardData && window.clipboardData.getData('text');
    } else {
        pasteText = clipboardData.getData('text/plain');
        pasteHtml = clipboardData.getData('text/html');
    }
    if (!pasteHtml && pasteText) {
        pasteHtml = '<p>' + replaceHtmlSymbol(pasteText) + '</p>';
    }
    if (!pasteHtml) {
        return;
    }

    // 杩囨护word涓姸鎬佽繃鏉ョ殑鏃犵敤瀛楃
    var docSplitHtml = pasteHtml.split('</html>');
    if (docSplitHtml.length === 2) {
        pasteHtml = docSplitHtml[0];
    }

    // 杩囨护鏃犵敤鏍囩
    pasteHtml = pasteHtml.replace(/<(meta|script|link).+?>/igm, '');
    // 鍘绘帀娉ㄩ噴
    pasteHtml = pasteHtml.replace(/<!--.*?-->/mg, '');
    // 杩囨护 data-xxx 灞炴€?    pasteHtml = pasteHtml.replace(/\s?data-.+?=('|").+?('|")/igm, '');

    if (ignoreImg) {
        // 蹇界暐鍥剧墖
        pasteHtml = pasteHtml.replace(/<img.+?>/igm, '');
    }

    if (filterStyle) {
        // 杩囨护鏍峰紡
        pasteHtml = pasteHtml.replace(/\s?(class|style)=('|").*?('|")/igm, '');
    } else {
        // 淇濈暀鏍峰紡
        pasteHtml = pasteHtml.replace(/\s?class=('|").*?('|")/igm, '');
    }

    return pasteHtml;
}

// 鑾峰彇绮樿创鐨勫浘鐗囨枃浠?function getPasteImgs(e) {
    var result = [];
    var txt = getPasteText(e);
    if (txt) {
        // 鏈夋枃瀛楋紝灏卞拷鐣ュ浘鐗?        return result;
    }

    var clipboardData = e.clipboardData || e.originalEvent && e.originalEvent.clipboardData || {};
    var items = clipboardData.items;
    if (!items) {
        return result;
    }

    objForEach(items, function (key, value) {
        var type = value.type;
        if (/image/i.test(type)) {
            result.push(value.getAsFile());
        }
    });

    return result;
}

/*
    缂栬緫鍖哄煙
*/

// 鑾峰彇涓€涓?elem.childNodes 鐨?JSON 鏁版嵁
function getChildrenJSON($elem) {
    var result = [];
    var $children = $elem.childNodes() || []; // 娉ㄦ剰 childNodes() 鍙互鑾峰彇鏂囨湰鑺傜偣
    $children.forEach(function (curElem) {
        var elemResult = void 0;
        var nodeType = curElem.nodeType;

        // 鏂囨湰鑺傜偣
        if (nodeType === 3) {
            elemResult = curElem.textContent;
            elemResult = replaceHtmlSymbol(elemResult);
        }

        // 鏅€?DOM 鑺傜偣
        if (nodeType === 1) {
            elemResult = {};

            // tag
            elemResult.tag = curElem.nodeName.toLowerCase();
            // attr
            var attrData = [];
            var attrList = curElem.attributes || {};
            var attrListLength = attrList.length || 0;
            for (var i = 0; i < attrListLength; i++) {
                var attr = attrList[i];
                attrData.push({
                    name: attr.name,
                    value: attr.value
                });
            }
            elemResult.attrs = attrData;
            // children锛堥€掑綊锛?            elemResult.children = getChildrenJSON($(curElem));
        }

        result.push(elemResult);
    });
    return result;
}

// 鏋勯€犲嚱鏁?function Text(editor) {
    this.editor = editor;
}

// 淇敼鍘熷瀷
Text.prototype = {
    constructor: Text,

    // 鍒濆鍖?    init: function init() {
        // 缁戝畾浜嬩欢
        this._bindEvent();
    },

    // 娓呯┖鍐呭
    clear: function clear() {
        this.html('<p><br></p>');
    },

    // 鑾峰彇 璁剧疆 html
    html: function html(val) {
        var editor = this.editor;
        var $textElem = editor.$textElem;
        var html = void 0;
        if (val == null) {
            html = $textElem.html();
            // 鏈€変腑浠讳綍鍐呭鐨勬椂鍊欑偣鍑烩€滃姞绮椻€濇垨鑰呪€滄枩浣撯€濈瓑鎸夐挳锛屽氨寰楅渶瑕佷竴涓┖鐨勫崰浣嶇 &#8203 锛岃繖閲屾浛鎹㈡帀
            html = html.replace(/\u200b/gm, '');
            return html;
        } else {
            $textElem.html(val);

            // 鍒濆鍖栭€夊彇锛屽皢鍏夋爣瀹氫綅鍒板唴瀹瑰熬閮?            editor.initSelection();
        }
    },

    // 鑾峰彇 JSON
    getJSON: function getJSON() {
        var editor = this.editor;
        var $textElem = editor.$textElem;
        return getChildrenJSON($textElem);
    },

    // 鑾峰彇 璁剧疆 text
    text: function text(val) {
        var editor = this.editor;
        var $textElem = editor.$textElem;
        var text = void 0;
        if (val == null) {
            text = $textElem.text();
            // 鏈€変腑浠讳綍鍐呭鐨勬椂鍊欑偣鍑烩€滃姞绮椻€濇垨鑰呪€滄枩浣撯€濈瓑鎸夐挳锛屽氨寰楅渶瑕佷竴涓┖鐨勫崰浣嶇 &#8203 锛岃繖閲屾浛鎹㈡帀
            text = text.replace(/\u200b/gm, '');
            return text;
        } else {
            $textElem.text('<p>' + val + '</p>');

            // 鍒濆鍖栭€夊彇锛屽皢鍏夋爣瀹氫綅鍒板唴瀹瑰熬閮?            editor.initSelection();
        }
    },

    // 杩藉姞鍐呭
    append: function append(html) {
        var editor = this.editor;
        var $textElem = editor.$textElem;
        $textElem.append($(html));

        // 鍒濆鍖栭€夊彇锛屽皢鍏夋爣瀹氫綅鍒板唴瀹瑰熬閮?        editor.initSelection();
    },

    // 缁戝畾浜嬩欢
    _bindEvent: function _bindEvent() {
        // 瀹炴椂淇濆瓨閫夊彇
        this._saveRangeRealTime();

        // 鎸夊洖杞﹀缓鏃剁殑鐗规畩澶勭悊
        this._enterKeyHandle();

        // 娓呯┖鏃朵繚鐣?<p><br></p>
        this._clearHandle();

        // 绮樿创浜嬩欢锛堢矘璐存枃瀛楋紝绮樿创鍥剧墖锛?        this._pasteHandle();

        // tab 鐗规畩澶勭悊
        this._tabHandle();

        // img 鐐瑰嚮
        this._imgHandle();

        // 鎷栨嫿浜嬩欢
        this._dragHandle();
    },

    // 瀹炴椂淇濆瓨閫夊彇
    _saveRangeRealTime: function _saveRangeRealTime() {
        var editor = this.editor;
        var $textElem = editor.$textElem;

        // 淇濆瓨褰撳墠鐨勯€夊尯
        function saveRange(e) {
            // 闅忔椂淇濆瓨閫夊尯
            editor.selection.saveRange();
            // 鏇存柊鎸夐挳 ative 鐘舵€?            editor.menus.changeActive();
        }
        // 鎸夐敭鍚庝繚瀛?        $textElem.on('keyup', saveRange);
        $textElem.on('mousedown', function (e) {
            // mousedown 鐘舵€佷笅锛岄紶鏍囨粦鍔ㄥ埌缂栬緫鍖哄煙澶栭潰锛屼篃闇€瑕佷繚瀛橀€夊尯
            $textElem.on('mouseleave', saveRange);
        });
        $textElem.on('mouseup', function (e) {
            saveRange();
            // 鍦ㄧ紪杈戝櫒鍖哄煙涔嬪唴瀹屾垚鐐瑰嚮锛屽彇娑堥紶鏍囨粦鍔ㄥ埌缂栬緫鍖哄闈㈢殑浜嬩欢
            $textElem.off('mouseleave', saveRange);
        });
    },

    // 鎸夊洖杞﹂敭鏃剁殑鐗规畩澶勭悊
    _enterKeyHandle: function _enterKeyHandle() {
        var editor = this.editor;
        var $textElem = editor.$textElem;

        function insertEmptyP($selectionElem) {
            var $p = $('<p><br></p>');
            $p.insertBefore($selectionElem);
            editor.selection.createRangeByElem($p, true);
            editor.selection.restoreSelection();
            $selectionElem.remove();
        }

        // 灏嗗洖杞︿箣鍚庣敓鎴愮殑闈?<p> 鐨勯《绾ф爣绛撅紝鏀逛负 <p>
        function pHandle(e) {
            var $selectionElem = editor.selection.getSelectionContainerElem();
            var $parentElem = $selectionElem.parent();

            if ($parentElem.html() === '<code><br></code>') {
                // 鍥炶溅涔嬪墠鍏夋爣鎵€鍦ㄤ竴涓?<p><code>.....</code></p> 锛屽拷鐒跺洖杞︾敓鎴愪竴涓┖鐨?<p><code><br></code></p>
                // 鑰屼笖缁х画鍥炶溅璺充笉鍑哄幓锛屽洜姝ゅ彧鑳界壒娈婂鐞?                insertEmptyP($selectionElem);
                return;
            }

            if (!$parentElem.equal($textElem)) {
                // 涓嶆槸椤剁骇鏍囩
                return;
            }

            var nodeName = $selectionElem.getNodeName();
            if (nodeName === 'P') {
                // 褰撳墠鐨勬爣绛炬槸 P 锛屼笉鐢ㄥ仛澶勭悊
                return;
            }

            if ($selectionElem.text()) {
                // 鏈夊唴瀹癸紝涓嶅仛澶勭悊
                return;
            }

            // 鎻掑叆 <p> 锛屽苟灏嗛€夊彇瀹氫綅鍒?<p>锛屽垹闄ゅ綋鍓嶆爣绛?            insertEmptyP($selectionElem);
        }

        $textElem.on('keyup', function (e) {
            if (e.keyCode !== 13) {
                // 涓嶆槸鍥炶溅閿?                return;
            }
            // 灏嗗洖杞︿箣鍚庣敓鎴愮殑闈?<p> 鐨勯《绾ф爣绛撅紝鏀逛负 <p>
            pHandle(e);
        });

        // <pre><code></code></pre> 鍥炶溅鏃?鐗规畩澶勭悊
        function codeHandle(e) {
            var $selectionElem = editor.selection.getSelectionContainerElem();
            if (!$selectionElem) {
                return;
            }
            var $parentElem = $selectionElem.parent();
            var selectionNodeName = $selectionElem.getNodeName();
            var parentNodeName = $parentElem.getNodeName();

            if (selectionNodeName !== 'CODE' || parentNodeName !== 'PRE') {
                // 涓嶇鍚堣姹?蹇界暐
                return;
            }

            if (!editor.cmd.queryCommandSupported('insertHTML')) {
                // 蹇呴』鍘熺敓鏀寔 insertHTML 鍛戒护
                return;
            }

            // 澶勭悊锛氬厜鏍囧畾浣嶅埌浠ｇ爜鏈熬锛岃仈绯荤偣鍑讳袱娆″洖杞︼紝鍗宠烦鍑轰唬鐮佸潡
            if (editor._willBreakCode === true) {
                // 姝ゆ椂鍙互璺冲嚭浠ｇ爜鍧?                // 鎻掑叆 <p> 锛屽苟灏嗛€夊彇瀹氫綅鍒?<p>
                var $p = $('<p><br></p>');
                $p.insertAfter($parentElem);
                editor.selection.createRangeByElem($p, true);
                editor.selection.restoreSelection();

                // 淇敼鐘舵€?                editor._willBreakCode = false;

                e.preventDefault();
                return;
            }

            var _startOffset = editor.selection.getRange().startOffset;

            // 澶勭悊锛氬洖杞︽椂锛屼笉鑳芥彃鍏?<br> 鑰屾槸鎻掑叆 \n 锛屽洜涓烘槸鍦?pre 鏍囩閲岄潰
            editor.cmd.do('insertHTML', '\n');
            editor.selection.saveRange();
            if (editor.selection.getRange().startOffset === _startOffset) {
                // 娌¤捣浣滅敤锛屽啀鏉ヤ竴閬?                editor.cmd.do('insertHTML', '\n');
            }

            var codeLength = $selectionElem.html().length;
            if (editor.selection.getRange().startOffset + 1 === codeLength) {
                // 璇存槑鍏夋爣鍦ㄤ唬鐮佹渶鍚庣殑浣嶇疆锛屾墽琛屼簡鍥炶溅鎿嶄綔
                // 璁板綍涓嬫潵锛屼互渚夸笅娆″洖杞︽椂鍊欒烦鍑?code
                editor._willBreakCode = true;
            }

            // 闃绘榛樿琛屼负
            e.preventDefault();
        }

        $textElem.on('keydown', function (e) {
            if (e.keyCode !== 13) {
                // 涓嶆槸鍥炶溅閿?                // 鍙栨秷鍗冲皢璺宠浆浠ｇ爜鍧楃殑璁板綍
                editor._willBreakCode = false;
                return;
            }
            // <pre><code></code></pre> 鍥炶溅鏃?鐗规畩澶勭悊
            codeHandle(e);
        });
    },

    // 娓呯┖鏃朵繚鐣?<p><br></p>
    _clearHandle: function _clearHandle() {
        var editor = this.editor;
        var $textElem = editor.$textElem;

        $textElem.on('keydown', function (e) {
            if (e.keyCode !== 8) {
                return;
            }
            var txtHtml = $textElem.html().toLowerCase().trim();
            if (txtHtml === '<p><br></p>') {
                // 鏈€鍚庡墿涓嬩竴涓┖琛岋紝灏变笉鍐嶅垹闄や簡
                e.preventDefault();
                return;
            }
        });

        $textElem.on('keyup', function (e) {
            if (e.keyCode !== 8) {
                return;
            }
            var $p = void 0;
            var txtHtml = $textElem.html().toLowerCase().trim();

            // firefox 鏃剁敤 txtHtml === '<br>' 鍒ゆ柇锛屽叾浠栫敤 !txtHtml 鍒ゆ柇
            if (!txtHtml || txtHtml === '<br>') {
                // 鍐呭绌轰簡
                $p = $('<p><br/></p>');
                $textElem.html(''); // 涓€瀹氳鍏堟竻绌猴紝鍚﹀垯鍦?firefox 涓嬫湁闂
                $textElem.append($p);
                editor.selection.createRangeByElem($p, false, true);
                editor.selection.restoreSelection();
            }
        });
    },

    // 绮樿创浜嬩欢锛堢矘璐存枃瀛?绮樿创鍥剧墖锛?    _pasteHandle: function _pasteHandle() {
        var editor = this.editor;
        var config = editor.config;
        var pasteFilterStyle = config.pasteFilterStyle;
        var pasteTextHandle = config.pasteTextHandle;
        var ignoreImg = config.pasteIgnoreImg;
        var $textElem = editor.$textElem;

        // 绮樿创鍥剧墖銆佹枃鏈殑浜嬩欢锛屾瘡娆″彧鑳芥墽琛屼竴涓?        // 鍒ゆ柇璇ユ绮樿创浜嬩欢鏄惁鍙互鎵ц
        var pasteTime = 0;
        function canDo() {
            var now = Date.now();
            var flag = false;
            if (now - pasteTime >= 100) {
                // 闂撮殧澶т簬 100 ms 锛屽彲浠ユ墽琛?                flag = true;
            }
            pasteTime = now;
            return flag;
        }
        function resetTime() {
            pasteTime = 0;
        }

        // 绮樿创鏂囧瓧
        $textElem.on('paste', function (e) {
            if (UA.isIE()) {
                return;
            } else {
                // 闃绘榛樿琛屼负锛屼娇鐢?execCommand 鐨勭矘璐村懡浠?                e.preventDefault();
            }

            // 绮樿创鍥剧墖鍜屾枃鏈紝鍙兘鍚屾椂浣跨敤涓€涓?            if (!canDo()) {
                return;
            }

            // 鑾峰彇绮樿创鐨勬枃瀛?            var pasteHtml = getPasteHtml(e, pasteFilterStyle, ignoreImg);
            var pasteText = getPasteText(e);
            pasteText = pasteText.replace(/\n/gm, '<br>');

            var $selectionElem = editor.selection.getSelectionContainerElem();
            if (!$selectionElem) {
                return;
            }
            var nodeName = $selectionElem.getNodeName();

            // code 涓彧鑳界矘璐寸函鏂囨湰
            if (nodeName === 'CODE' || nodeName === 'PRE') {
                if (pasteTextHandle && isFunction(pasteTextHandle)) {
                    // 鐢ㄦ埛鑷畾涔夎繃婊ゅ鐞嗙矘璐村唴瀹?                    pasteText = '' + (pasteTextHandle(pasteText) || '');
                }
                editor.cmd.do('insertHTML', '<p>' + pasteText + '</p>');
                return;
            }

            // 鍏堟斁寮€娉ㄩ噴锛屾湁闂鍐嶈拷鏌?鈥斺€斺€斺€?            // // 琛ㄦ牸涓拷鐣ワ紝鍙兘浼氬嚭鐜板紓甯搁棶棰?            // if (nodeName === 'TD' || nodeName === 'TH') {
            //     return
            // }

            if (!pasteHtml) {
                // 娌℃湁鍐呭锛屽彲缁х画鎵ц涓嬮潰鐨勫浘鐗囩矘璐?                resetTime();
                return;
            }
            try {
                // firefox 涓紝鑾峰彇鐨?pasteHtml 鍙兘鏄病鏈?<ul> 鍖呰９鐨?<li>
                // 鍥犳鎵ц insertHTML 浼氭姤閿?                if (pasteTextHandle && isFunction(pasteTextHandle)) {
                    // 鐢ㄦ埛鑷畾涔夎繃婊ゅ鐞嗙矘璐村唴瀹?                    pasteHtml = '' + (pasteTextHandle(pasteHtml) || '');
                }
                editor.cmd.do('insertHTML', pasteHtml);
            } catch (ex) {
                // 姝ゆ椂浣跨敤 pasteText 鏉ュ吋瀹逛竴涓?                if (pasteTextHandle && isFunction(pasteTextHandle)) {
                    // 鐢ㄦ埛鑷畾涔夎繃婊ゅ鐞嗙矘璐村唴瀹?                    pasteText = '' + (pasteTextHandle(pasteText) || '');
                }
                editor.cmd.do('insertHTML', '<p>' + pasteText + '</p>');
            }
        });

        // 绮樿创鍥剧墖
        $textElem.on('paste', function (e) {
            if (UA.isIE()) {
                return;
            } else {
                e.preventDefault();
            }

            // 绮樿创鍥剧墖鍜屾枃鏈紝鍙兘鍚屾椂浣跨敤涓€涓?            if (!canDo()) {
                return;
            }

            // 鑾峰彇绮樿创鐨勫浘鐗?            var pasteFiles = getPasteImgs(e);
            if (!pasteFiles || !pasteFiles.length) {
                return;
            }

            // 鑾峰彇褰撳墠鐨勫厓绱?            var $selectionElem = editor.selection.getSelectionContainerElem();
            if (!$selectionElem) {
                return;
            }
            var nodeName = $selectionElem.getNodeName();

            // code 涓矘璐村拷鐣?            if (nodeName === 'CODE' || nodeName === 'PRE') {
                return;
            }

            // 涓婁紶鍥剧墖
            var uploadImg = editor.uploadImg;
            uploadImg.uploadImg(pasteFiles);
        });
    },

    // tab 鐗规畩澶勭悊
    _tabHandle: function _tabHandle() {
        var editor = this.editor;
        var $textElem = editor.$textElem;

        $textElem.on('keydown', function (e) {
            if (e.keyCode !== 9) {
                return;
            }
            if (!editor.cmd.queryCommandSupported('insertHTML')) {
                // 蹇呴』鍘熺敓鏀寔 insertHTML 鍛戒护
                return;
            }
            var $selectionElem = editor.selection.getSelectionContainerElem();
            if (!$selectionElem) {
                return;
            }
            var $parentElem = $selectionElem.parent();
            var selectionNodeName = $selectionElem.getNodeName();
            var parentNodeName = $parentElem.getNodeName();

            if (selectionNodeName === 'CODE' && parentNodeName === 'PRE') {
                // <pre><code> 閲岄潰
                editor.cmd.do('insertHTML', '    ');
            } else {
                // 鏅€氭枃瀛?                editor.cmd.do('insertHTML', '&nbsp;&nbsp;&nbsp;&nbsp;');
            }

            e.preventDefault();
        });
    },

    // img 鐐瑰嚮
    _imgHandle: function _imgHandle() {
        var editor = this.editor;
        var $textElem = editor.$textElem;

        // 涓哄浘鐗囧鍔?selected 鏍峰紡
        $textElem.on('click', 'img', function (e) {
            var img = this;
            var $img = $(img);

            if ($img.attr('data-w-e') === '1') {
                // 鏄〃鎯呭浘鐗囷紝蹇界暐
                return;
            }

            // 璁板綍褰撳墠鐐瑰嚮杩囩殑鍥剧墖
            editor._selectedImg = $img;

            // 淇敼閫夊尯骞?restore 锛岄槻姝㈢敤鎴锋鏃剁偣鍑婚€€鏍奸敭锛屼細鍒犻櫎鍏朵粬鍐呭
            editor.selection.createRangeByElem($img);
            editor.selection.restoreSelection();
        });

        // 鍘绘帀鍥剧墖鐨?selected 鏍峰紡
        $textElem.on('click  keyup', function (e) {
            if (e.target.matches('img')) {
                // 鐐瑰嚮鐨勬槸鍥剧墖锛屽拷鐣?                return;
            }
            // 鍒犻櫎璁板綍
            editor._selectedImg = null;
        });
    },

    // 鎷栨嫿浜嬩欢
    _dragHandle: function _dragHandle() {
        var editor = this.editor;

        // 绂佺敤 document 鎷栨嫿浜嬩欢
        var $document = $(document);
        $document.on('dragleave drop dragenter dragover', function (e) {
            e.preventDefault();
        });

        // 娣诲姞缂栬緫鍖哄煙鎷栨嫿浜嬩欢
        var $textElem = editor.$textElem;
        $textElem.on('drop', function (e) {
            e.preventDefault();
            var files = e.dataTransfer && e.dataTransfer.files;
            if (!files || !files.length) {
                return;
            }

            // 涓婁紶鍥剧墖
            var uploadImg = editor.uploadImg;
            uploadImg.uploadImg(files);
        });
    }
};

/*
    鍛戒护锛屽皝瑁?document.execCommand
*/

// 鏋勯€犲嚱鏁?function Command(editor) {
    this.editor = editor;
}

// 淇敼鍘熷瀷
Command.prototype = {
    constructor: Command,

    // 鎵ц鍛戒护
    do: function _do(name, value) {
        var editor = this.editor;

        // 浣跨敤 styleWithCSS
        if (!editor._useStyleWithCSS) {
            document.execCommand('styleWithCSS', null, true);
            editor._useStyleWithCSS = true;
        }

        // 濡傛灉鏃犻€夊尯锛屽拷鐣?        if (!editor.selection.getRange()) {
            return;
        }

        // 鎭㈠閫夊彇
        editor.selection.restoreSelection();

        // 鎵ц
        var _name = '_' + name;
        if (this[_name]) {
            // 鏈夎嚜瀹氫箟浜嬩欢
            this[_name](value);
        } else {
            // 榛樿 command
            this._execCommand(name, value);
        }

        // 淇敼鑿滃崟鐘舵€?        editor.menus.changeActive();

        // 鏈€鍚庯紝鎭㈠閫夊彇淇濊瘉鍏夋爣鍦ㄥ師鏉ョ殑浣嶇疆闂儊
        editor.selection.saveRange();
        editor.selection.restoreSelection();

        // 瑙﹀彂 onchange
        editor.change && editor.change();
    },

    // 鑷畾涔?insertHTML 浜嬩欢
    _insertHTML: function _insertHTML(html) {
        var editor = this.editor;
        var range = editor.selection.getRange();

        if (this.queryCommandSupported('insertHTML')) {
            // W3C
            this._execCommand('insertHTML', html);
        } else if (range.insertNode) {
            // IE
            range.deleteContents();
            range.insertNode($(html)[0]);
        } else if (range.pasteHTML) {
            // IE <= 10
            range.pasteHTML(html);
        }
    },

    // 鎻掑叆 elem
    _insertElem: function _insertElem($elem) {
        var editor = this.editor;
        var range = editor.selection.getRange();

        if (range.insertNode) {
            range.deleteContents();
            range.insertNode($elem[0]);
        }
    },

    // 灏佽 execCommand
    _execCommand: function _execCommand(name, value) {
        document.execCommand(name, false, value);
    },

    // 灏佽 document.queryCommandValue
    queryCommandValue: function queryCommandValue(name) {
        return document.queryCommandValue(name);
    },

    // 灏佽 document.queryCommandState
    queryCommandState: function queryCommandState(name) {
        return document.queryCommandState(name);
    },

    // 灏佽 document.queryCommandSupported
    queryCommandSupported: function queryCommandSupported(name) {
        return document.queryCommandSupported(name);
    }
};

/*
    selection range API
*/

// 鏋勯€犲嚱鏁?function API(editor) {
    this.editor = editor;
    this._currentRange = null;
}

// 淇敼鍘熷瀷
API.prototype = {
    constructor: API,

    // 鑾峰彇 range 瀵硅薄
    getRange: function getRange() {
        return this._currentRange;
    },

    // 淇濆瓨閫夊尯
    saveRange: function saveRange(_range) {
        if (_range) {
            // 淇濆瓨宸叉湁閫夊尯
            this._currentRange = _range;
            return;
        }

        // 鑾峰彇褰撳墠鐨勯€夊尯
        var selection = window.getSelection();
        if (selection.rangeCount === 0) {
            return;
        }
        var range = selection.getRangeAt(0);

        // 鍒ゆ柇閫夊尯鍐呭鏄惁鍦ㄧ紪杈戝唴瀹逛箣鍐?        var $containerElem = this.getSelectionContainerElem(range);
        if (!$containerElem) {
            return;
        }

        // 鍒ゆ柇閫夊尯鍐呭鏄惁鍦ㄤ笉鍙紪杈戝尯鍩熶箣鍐?        if ($containerElem.attr('contenteditable') === 'false' || $containerElem.parentUntil('[contenteditable=false]')) {
            return;
        }

        var editor = this.editor;
        var $textElem = editor.$textElem;
        if ($textElem.isContain($containerElem)) {
            // 鏄紪杈戝唴瀹逛箣鍐呯殑
            this._currentRange = range;
        }
    },

    // 鎶樺彔閫夊尯
    collapseRange: function collapseRange(toStart) {
        if (toStart == null) {
            // 榛樿涓?false
            toStart = false;
        }
        var range = this._currentRange;
        if (range) {
            range.collapse(toStart);
        }
    },

    // 閫変腑鍖哄煙鐨勬枃瀛?    getSelectionText: function getSelectionText() {
        var range = this._currentRange;
        if (range) {
            return this._currentRange.toString();
        } else {
            return '';
        }
    },

    // 閫夊尯鐨?$Elem
    getSelectionContainerElem: function getSelectionContainerElem(range) {
        range = range || this._currentRange;
        var elem = void 0;
        if (range) {
            elem = range.commonAncestorContainer;
            return $(elem.nodeType === 1 ? elem : elem.parentNode);
        }
    },
    getSelectionStartElem: function getSelectionStartElem(range) {
        range = range || this._currentRange;
        var elem = void 0;
        if (range) {
            elem = range.startContainer;
            return $(elem.nodeType === 1 ? elem : elem.parentNode);
        }
    },
    getSelectionEndElem: function getSelectionEndElem(range) {
        range = range || this._currentRange;
        var elem = void 0;
        if (range) {
            elem = range.endContainer;
            return $(elem.nodeType === 1 ? elem : elem.parentNode);
        }
    },

    // 閫夊尯鏄惁涓虹┖
    isSelectionEmpty: function isSelectionEmpty() {
        var range = this._currentRange;
        if (range && range.startContainer) {
            if (range.startContainer === range.endContainer) {
                if (range.startOffset === range.endOffset) {
                    return true;
                }
            }
        }
        return false;
    },

    // 鎭㈠閫夊尯
    restoreSelection: function restoreSelection() {
        var selection = window.getSelection();
        selection.removeAllRanges();
        selection.addRange(this._currentRange);
    },

    // 鍒涘缓涓€涓┖鐧斤紙鍗?&#8203 瀛楃锛夐€夊尯
    createEmptyRange: function createEmptyRange() {
        var editor = this.editor;
        var range = this.getRange();
        var $elem = void 0;

        if (!range) {
            // 褰撳墠鏃?range
            return;
        }
        if (!this.isSelectionEmpty()) {
            // 褰撳墠閫夊尯蹇呴』娌℃湁鍐呭鎵嶅彲浠?            return;
        }

        try {
            // 鐩墠鍙敮鎸?webkit 鍐呮牳
            if (UA.isWebkit()) {
                // 鎻掑叆 &#8203
                editor.cmd.do('insertHTML', '&#8203;');
                // 淇敼 offset 浣嶇疆
                range.setEnd(range.endContainer, range.endOffset + 1);
                // 瀛樺偍
                this.saveRange(range);
            } else {
                $elem = $('<strong>&#8203;</strong>');
                editor.cmd.do('insertElem', $elem);
                this.createRangeByElem($elem, true);
            }
        } catch (ex) {
            // 閮ㄥ垎鎯呭喌涓嬩細鎶ラ敊锛屽吋瀹逛竴涓?        }
    },

    // 鏍规嵁 $Elem 璁剧疆閫夊尯
    createRangeByElem: function createRangeByElem($elem, toStart, isContent) {
        // $elem - 缁忚繃灏佽鐨?elem
        // toStart - true 寮€濮嬩綅缃紝false 缁撴潫浣嶇疆
        // isContent - 鏄惁閫変腑Elem鐨勫唴瀹?        if (!$elem.length) {
            return;
        }

        var elem = $elem[0];
        var range = document.createRange();

        if (isContent) {
            range.selectNodeContents(elem);
        } else {
            range.selectNode(elem);
        }

        if (typeof toStart === 'boolean') {
            range.collapse(toStart);
        }

        // 瀛樺偍 range
        this.saveRange(range);
    }
};

/*
    涓婁紶杩涘害鏉?*/

function Progress(editor) {
    this.editor = editor;
    this._time = 0;
    this._isShow = false;
    this._isRender = false;
    this._timeoutId = 0;
    this.$textContainer = editor.$textContainerElem;
    this.$bar = $('<div class="w-e-progress"></div>');
}

Progress.prototype = {
    constructor: Progress,

    show: function show(progress) {
        var _this = this;

        // 鐘舵€佸鐞?        if (this._isShow) {
            return;
        }
        this._isShow = true;

        // 娓叉煋
        var $bar = this.$bar;
        if (!this._isRender) {
            var $textContainer = this.$textContainer;
            $textContainer.append($bar);
        } else {
            this._isRender = true;
        }

        // 鏀瑰彉杩涘害锛堣妭娴侊紝100ms 娓叉煋涓€娆★級
        if (Date.now() - this._time > 100) {
            if (progress <= 1) {
                $bar.css('width', progress * 100 + '%');
                this._time = Date.now();
            }
        }

        // 闅愯棌
        var timeoutId = this._timeoutId;
        if (timeoutId) {
            clearTimeout(timeoutId);
        }
        timeoutId = setTimeout(function () {
            _this._hide();
        }, 500);
    },

    _hide: function _hide() {
        var $bar = this.$bar;
        $bar.remove();

        // 淇敼鐘舵€?        this._time = 0;
        this._isShow = false;
        this._isRender = false;
    }
};

var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) {
  return typeof obj;
} : function (obj) {
  return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj;
};

/*
    涓婁紶鍥剧墖
*/

// 鏋勯€犲嚱鏁?function UploadImg(editor) {
    this.editor = editor;
}

// 鍘熷瀷
UploadImg.prototype = {
    constructor: UploadImg,

    // 鏍规嵁 debug 寮瑰嚭涓嶅悓鐨勪俊鎭?    _alert: function _alert(alertInfo, debugInfo) {
        var editor = this.editor;
        var debug = editor.config.debug;
        var customAlert = editor.config.customAlert;

        if (debug) {
            throw new Error('wangEditor: ' + (debugInfo || alertInfo));
        } else {
            if (customAlert && typeof customAlert === 'function') {
                customAlert(alertInfo);
            } else {
                alert(alertInfo);
            }
        }
    },

    // 鏍规嵁閾炬帴鎻掑叆鍥剧墖
    insertLinkImg: function insertLinkImg(link) {
        var _this2 = this;

        if (!link) {
            return;
        }
        var editor = this.editor;
        var config = editor.config;

        // 鏍￠獙鏍煎紡
        var linkImgCheck = config.linkImgCheck;
        var checkResult = void 0;
        if (linkImgCheck && typeof linkImgCheck === 'function') {
            checkResult = linkImgCheck(link);
            if (typeof checkResult === 'string') {
                // 鏍￠獙澶辫触锛屾彁绀轰俊鎭?                alert(checkResult);
                return;
            }
        }

        editor.cmd.do('insertHTML', '<img src="' + link + '" style="max-width:100%;"/>');

        // 楠岃瘉鍥剧墖 url 鏄惁鏈夋晥锛屾棤鏁堢殑璇濈粰鍑烘彁绀?        var img = document.createElement('img');
        img.onload = function () {
            var callback = config.linkImgCallback;
            if (callback && typeof callback === 'function') {
                callback(link);
            }

            img = null;
        };
        img.onerror = function () {
            img = null;
            // 鏃犳硶鎴愬姛涓嬭浇鍥剧墖
            _this2._alert('鎻掑叆鍥剧墖閿欒', 'wangEditor: \u63D2\u5165\u56FE\u7247\u51FA\u9519\uFF0C\u56FE\u7247\u94FE\u63A5\u662F "' + link + '"\uFF0C\u4E0B\u8F7D\u8BE5\u94FE\u63A5\u5931\u8D25');
            return;
        };
        img.onabort = function () {
            img = null;
        };
        img.src = link;
    },

    // 涓婁紶鍥剧墖
    uploadImg: function uploadImg(files) {
        var _this3 = this;

        if (!files || !files.length) {
            return;
        }

        // ------------------------------ 鑾峰彇閰嶇疆淇℃伅 ------------------------------
        var editor = this.editor;
        var config = editor.config;
        var uploadImgServer = config.uploadImgServer;
        var uploadImgShowBase64 = config.uploadImgShowBase64;

        var maxSize = config.uploadImgMaxSize;
        var maxSizeM = maxSize / 1024 / 1024;
        var maxLength = config.uploadImgMaxLength || 10000;
        var uploadFileName = config.uploadFileName || '';
        var uploadImgParams = config.uploadImgParams || {};
        var uploadImgParamsWithUrl = config.uploadImgParamsWithUrl;
        var uploadImgHeaders = config.uploadImgHeaders || {};
        var hooks = config.uploadImgHooks || {};
        var timeout = config.uploadImgTimeout || 3000;
        var withCredentials = config.withCredentials;
        if (withCredentials == null) {
            withCredentials = false;
        }
        var customUploadImg = config.customUploadImg;

        if (!customUploadImg) {
            // 娌℃湁 customUploadImg 鐨勬儏鍐典笅锛岄渶瑕佸涓嬩袱涓厤缃墠鑳界户缁繘琛屽浘鐗囦笂浼?            if (!uploadImgServer && !uploadImgShowBase64) {
                return;
            }
        }

        // ------------------------------ 楠岃瘉鏂囦欢淇℃伅 ------------------------------
        var resultFiles = [];
        var errInfo = [];
        arrForEach(files, function (file) {
            var name = file.name;
            var size = file.size;

            // chrome 浣庣増鏈?name === undefined
            if (!name || !size) {
                return;
            }

            if (/\.(jpg|jpeg|png|bmp|gif|webp)$/i.test(name) === false) {
                // 鍚庣紑鍚嶄笉鍚堟硶锛屼笉鏄浘鐗?                errInfo.push('\u3010' + name + '\u3011\u4E0D\u662F\u56FE\u7247');
                return;
            }
            if (maxSize < size) {
                // 涓婁紶鍥剧墖杩囧ぇ
                errInfo.push('\u3010' + name + '\u3011\u5927\u4E8E ' + maxSizeM + 'M');
                return;
            }

            // 楠岃瘉閫氳繃鐨勫姞鍏ョ粨鏋滃垪琛?            resultFiles.push(file);
        });
        // 鎶涘嚭楠岃瘉淇℃伅
        if (errInfo.length) {
            this._alert('鍥剧墖楠岃瘉鏈€氳繃: \n' + errInfo.join('\n'));
            return;
        }
        if (resultFiles.length > maxLength) {
            this._alert('涓€娆℃渶澶氫笂浼? + maxLength + '寮犲浘鐗?);
            return;
        }

        // ------------------------------ 鑷畾涔変笂浼?------------------------------
        if (customUploadImg && typeof customUploadImg === 'function') {
            customUploadImg(resultFiles, this.insertLinkImg.bind(this));

            // 闃绘浠ヤ笅浠ｇ爜鎵ц
            return;
        }

        // 娣诲姞鍥剧墖鏁版嵁
        var formdata = new FormData();
        arrForEach(resultFiles, function (file) {
            var name = uploadFileName || file.name;
            formdata.append(name, file);
        });

        // ------------------------------ 涓婁紶鍥剧墖 ------------------------------
        if (uploadImgServer && typeof uploadImgServer === 'string') {
            // 娣诲姞鍙傛暟
            var uploadImgServerArr = uploadImgServer.split('#');
            uploadImgServer = uploadImgServerArr[0];
            var uploadImgServerHash = uploadImgServerArr[1] || '';
            objForEach(uploadImgParams, function (key, val) {
                // 鍥犱娇鐢ㄨ€呭弽搴旓紝鑷畾涔夊弬鏁颁笉鑳介粯璁?encode 锛岀敱 v3.1.1 鐗堟湰寮€濮嬫敞閲婃帀
                // val = encodeURIComponent(val)

                // 绗竴锛屽皢鍙傛暟鎷兼帴鍒?url 涓?                if (uploadImgParamsWithUrl) {
                    if (uploadImgServer.indexOf('?') > 0) {
                        uploadImgServer += '&';
                    } else {
                        uploadImgServer += '?';
                    }
                    uploadImgServer = uploadImgServer + key + '=' + val;
                }

                // 绗簩锛屽皢鍙傛暟娣诲姞鍒?formdata 涓?                formdata.append(key, val);
            });
            if (uploadImgServerHash) {
                uploadImgServer += '#' + uploadImgServerHash;
            }

            // 瀹氫箟 xhr
            var xhr = new XMLHttpRequest();
            xhr.open('POST', uploadImgServer);

            // 璁剧疆瓒呮椂
            xhr.timeout = timeout;
            xhr.ontimeout = function () {
                // hook - timeout
                if (hooks.timeout && typeof hooks.timeout === 'function') {
                    hooks.timeout(xhr, editor);
                }

                _this3._alert('涓婁紶鍥剧墖瓒呮椂');
            };

            // 鐩戞帶 progress
            if (xhr.upload) {
                xhr.upload.onprogress = function (e) {
                    var percent = void 0;
                    // 杩涘害鏉?                    var progressBar = new Progress(editor);
                    if (e.lengthComputable) {
                        percent = e.loaded / e.total;
                        progressBar.show(percent);
                    }
                };
            }

            // 杩斿洖鏁版嵁
            xhr.onreadystatechange = function () {
                var result = void 0;
                if (xhr.readyState === 4) {
                    if (xhr.status < 200 || xhr.status >= 300) {
                        // hook - error
                        if (hooks.error && typeof hooks.error === 'function') {
                            hooks.error(xhr, editor);
                        }

                        // xhr 杩斿洖鐘舵€侀敊璇?                        _this3._alert('涓婁紶鍥剧墖鍙戠敓閿欒', '\u4E0A\u4F20\u56FE\u7247\u53D1\u751F\u9519\u8BEF\uFF0C\u670D\u52A1\u5668\u8FD4\u56DE\u72B6\u6001\u662F ' + xhr.status);
                        return;
                    }

                    result = xhr.responseText;
                    if ((typeof result === 'undefined' ? 'undefined' : _typeof(result)) !== 'object') {
                        try {
                            result = JSON.parse(result);
                        } catch (ex) {
                            // hook - fail
                            if (hooks.fail && typeof hooks.fail === 'function') {
                                hooks.fail(xhr, editor, result);
                            }

                            _this3._alert('涓婁紶鍥剧墖澶辫触', '涓婁紶鍥剧墖杩斿洖缁撴灉閿欒锛岃繑鍥炵粨鏋滄槸: ' + result);
                            return;
                        }
                    }
                    if (!hooks.customInsert && result.errno != '0') {
                        // hook - fail
                        if (hooks.fail && typeof hooks.fail === 'function') {
                            hooks.fail(xhr, editor, result);
                        }

                        // 鏁版嵁閿欒
                        _this3._alert('涓婁紶鍥剧墖澶辫触', '涓婁紶鍥剧墖杩斿洖缁撴灉閿欒锛岃繑鍥炵粨鏋?errno=' + result.errno);
                    } else {
                        if (hooks.customInsert && typeof hooks.customInsert === 'function') {
                            // 浣跨敤鑰呰嚜瀹氫箟鎻掑叆鏂规硶
                            hooks.customInsert(_this3.insertLinkImg.bind(_this3), result, editor);
                        } else {
                            // 灏嗗浘鐗囨彃鍏ョ紪杈戝櫒
                            var data = result.data || [];
                            data.forEach(function (link) {
                                _this3.insertLinkImg(link);
                            });
                        }

                        // hook - success
                        if (hooks.success && typeof hooks.success === 'function') {
                            hooks.success(xhr, editor, result);
                        }
                    }
                }
            };

            // hook - before
            if (hooks.before && typeof hooks.before === 'function') {
                var beforeResult = hooks.before(xhr, editor, resultFiles);
                if (beforeResult && (typeof beforeResult === 'undefined' ? 'undefined' : _typeof(beforeResult)) === 'object') {
                    if (beforeResult.prevent) {
                        // 濡傛灉杩斿洖鐨勭粨鏋滄槸 {prevent: true, msg: 'xxxx'} 鍒欒〃绀虹敤鎴锋斁寮冧笂浼?                        this._alert(beforeResult.msg);
                        return;
                    }
                }
            }

            // 鑷畾涔?headers
            objForEach(uploadImgHeaders, function (key, val) {
                xhr.setRequestHeader(key, val);
            });

            // 璺ㄥ煙浼?cookie
            xhr.withCredentials = withCredentials;

            // 鍙戦€佽姹?            xhr.send(formdata);

            // 娉ㄦ剰锛岃 return 銆備笉鍘绘搷浣滄帴涓嬫潵鐨?base64 鏄剧ず鏂瑰紡
            return;
        }

        // ------------------------------ 鏄剧ず base64 鏍煎紡 ------------------------------
        if (uploadImgShowBase64) {
            arrForEach(files, function (file) {
                var _this = _this3;
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function () {
                    _this.insertLinkImg(this.result);
                };
            });
        }
    }
};

/*
    缂栬緫鍣ㄦ瀯閫犲嚱鏁?*/

// id锛岀疮鍔?var editorId = 1;

// 鏋勯€犲嚱鏁?function Editor(toolbarSelector, textSelector) {
    if (toolbarSelector == null) {
        // 娌℃湁浼犲叆浠讳綍鍙傛暟锛屾姤閿?        throw new Error('閿欒锛氬垵濮嬪寲缂栬緫鍣ㄦ椂鍊欐湭浼犲叆浠讳綍鍙傛暟锛岃鏌ラ槄鏂囨。');
    }
    // id锛岀敤浠ュ尯鍒嗗崟涓〉闈笉鍚岀殑缂栬緫鍣ㄥ璞?    this.id = 'wangEditor-' + editorId++;

    this.toolbarSelector = toolbarSelector;
    this.textSelector = textSelector;

    // 鑷畾涔夐厤缃?    this.customConfig = {};
}

// 淇敼鍘熷瀷
Editor.prototype = {
    constructor: Editor,

    // 鍒濆鍖栭厤缃?    _initConfig: function _initConfig() {
        // _config 鏄粯璁ら厤缃紝this.customConfig 鏄敤鎴疯嚜瀹氫箟閰嶇疆锛屽皢瀹冧滑 merge 涔嬪悗鍐嶈祴鍊?        var target = {};
        this.config = Object.assign(target, config, this.customConfig);

        // 灏嗚瑷€閰嶇疆锛岀敓鎴愭鍒欒〃杈惧紡
        var langConfig = this.config.lang || {};
        var langArgs = [];
        objForEach(langConfig, function (key, val) {
            // key 鍗抽渶瑕佺敓鎴愭鍒欒〃杈惧紡鐨勮鍒欙紝濡傗€滄彃鍏ラ摼鎺モ€?            // val 鍗抽渶瑕佽鏇挎崲鎴愮殑璇█锛屽鈥渋nsert link鈥?            langArgs.push({
                reg: new RegExp(key, 'img'),
                val: val

            });
        });
        this.config.langArgs = langArgs;
    },

    // 鍒濆鍖?DOM
    _initDom: function _initDom() {
        var _this = this;

        var toolbarSelector = this.toolbarSelector;
        var $toolbarSelector = $(toolbarSelector);
        var textSelector = this.textSelector;

        var config$$1 = this.config;
        var zIndex = config$$1.zIndex;

        // 瀹氫箟鍙橀噺
        var $toolbarElem = void 0,
            $textContainerElem = void 0,
            $textElem = void 0,
            $children = void 0;

        if (textSelector == null) {
            // 鍙紶鍏ヤ竴涓弬鏁帮紝鍗虫槸瀹瑰櫒鐨勯€夋嫨鍣ㄦ垨鍏冪礌锛宼oolbar 鍜?text 鐨勫厓绱犺嚜琛屽垱寤?            $toolbarElem = $('<div></div>');
            $textContainerElem = $('<div></div>');

            // 灏嗙紪杈戝櫒鍖哄煙鍘熸湁鐨勫唴瀹癸紝鏆傚瓨璧锋潵
            $children = $toolbarSelector.children();

            // 娣诲姞鍒?DOM 缁撴瀯涓?            $toolbarSelector.append($toolbarElem).append($textContainerElem);

            // 鑷鍒涘缓鐨勶紝闇€瑕侀厤缃粯璁ょ殑鏍峰紡
            $toolbarElem.css('background-color', '#f1f1f1').css('border', '1px solid #ccc');
            $textContainerElem.css('border', '1px solid #ccc').css('border-top', 'none').css('height', '300px');
        } else {
            // toolbar 鍜?text 鐨勯€夋嫨鍣ㄩ兘鏈夊€硷紝璁板綍灞炴€?            $toolbarElem = $toolbarSelector;
            $textContainerElem = $(textSelector);
            // 灏嗙紪杈戝櫒鍖哄煙鍘熸湁鐨勫唴瀹癸紝鏆傚瓨璧锋潵
            $children = $textContainerElem.children();
        }

        // 缂栬緫鍖哄煙
        $textElem = $('<div></div>');
        $textElem.attr('contenteditable', 'true').css('width', '100%').css('height', '100%');

        // 鍒濆鍖栫紪杈戝尯鍩熷唴瀹?        if ($children && $children.length) {
            $textElem.append($children);
        } else {
            $textElem.append($('<p><br></p>'));
        }

        // 缂栬緫鍖哄煙鍔犲叆DOM
        $textContainerElem.append($textElem);

        // 璁剧疆閫氱敤鐨?class
        $toolbarElem.addClass('w-e-toolbar');
        $textContainerElem.addClass('w-e-text-container');
        $textContainerElem.css('z-index', zIndex);
        $textElem.addClass('w-e-text');

        // 娣诲姞 ID
        var toolbarElemId = getRandom('toolbar-elem');
        $toolbarElem.attr('id', toolbarElemId);
        var textElemId = getRandom('text-elem');
        $textElem.attr('id', textElemId);

        // 璁板綍灞炴€?        this.$toolbarElem = $toolbarElem;
        this.$textContainerElem = $textContainerElem;
        this.$textElem = $textElem;
        this.toolbarElemId = toolbarElemId;
        this.textElemId = textElemId;

        // 璁板綍杈撳叆娉曠殑寮€濮嬪拰缁撴潫
        var compositionEnd = true;
        $textContainerElem.on('compositionstart', function () {
            // 杈撳叆娉曞紑濮嬭緭鍏?            compositionEnd = false;
        });
        $textContainerElem.on('compositionend', function () {
            // 杈撳叆娉曠粨鏉熻緭鍏?            compositionEnd = true;
        });

        // 缁戝畾 onchange
        $textContainerElem.on('click keyup', function () {
            // 杈撳叆娉曠粨鏉熸墠鍑哄彂 onchange
            compositionEnd && _this.change && _this.change();
        });
        $toolbarElem.on('click', function () {
            this.change && this.change();
        });

        //缁戝畾 onfocus 涓?onblur 浜嬩欢
        if (config$$1.onfocus || config$$1.onblur) {
            // 褰撳墠缂栬緫鍣ㄦ槸鍚︽槸鐒︾偣鐘舵€?            this.isFocus = false;

            $(document).on('click', function (e) {
                //鍒ゆ柇褰撳墠鐐瑰嚮鍏冪礌鏄惁鍦ㄧ紪杈戝櫒鍐?                var isChild = $textElem.isContain($(e.target));

                //鍒ゆ柇褰撳墠鐐瑰嚮鍏冪礌鏄惁涓哄伐鍏锋爮
                var isToolbar = $toolbarElem.isContain($(e.target));
                var isMenu = $toolbarElem[0] == e.target ? true : false;

                if (!isChild) {
                    //鑻ヤ负閫夋嫨宸ュ叿鏍忎腑鐨勫姛鑳斤紝鍒欎笉瑙嗕负鎴恇lur鎿嶄綔
                    if (isToolbar && !isMenu) {
                        return;
                    }

                    if (_this.isFocus) {
                        _this.onblur && _this.onblur();
                    }
                    _this.isFocus = false;
                } else {
                    if (!_this.isFocus) {
                        _this.onfocus && _this.onfocus();
                    }
                    _this.isFocus = true;
                }
            });
        }
    },

    // 灏佽 command
    _initCommand: function _initCommand() {
        this.cmd = new Command(this);
    },

    // 灏佽 selection range API
    _initSelectionAPI: function _initSelectionAPI() {
        this.selection = new API(this);
    },

    // 娣诲姞鍥剧墖涓婁紶
    _initUploadImg: function _initUploadImg() {
        this.uploadImg = new UploadImg(this);
    },

    // 鍒濆鍖栬彍鍗?    _initMenus: function _initMenus() {
        this.menus = new Menus(this);
        this.menus.init();
    },

    // 娣诲姞 text 鍖哄煙
    _initText: function _initText() {
        this.txt = new Text(this);
        this.txt.init();
    },

    // 鍒濆鍖栭€夊尯锛屽皢鍏夋爣瀹氫綅鍒板唴瀹瑰熬閮?    initSelection: function initSelection(newLine) {
        var $textElem = this.$textElem;
        var $children = $textElem.children();
        if (!$children.length) {
            // 濡傛灉缂栬緫鍣ㄥ尯鍩熸棤鍐呭锛屾坊鍔犱竴涓┖琛岋紝閲嶆柊璁剧疆閫夊尯
            $textElem.append($('<p><br></p>'));
            this.initSelection();
            return;
        }

        var $last = $children.last();

        if (newLine) {
            // 鏂板涓€涓┖琛?            var html = $last.html().toLowerCase();
            var nodeName = $last.getNodeName();
            if (html !== '<br>' && html !== '<br\/>' || nodeName !== 'P') {
                // 鏈€鍚庝竴涓厓绱犱笉鏄?<p><br></p>锛屾坊鍔犱竴涓┖琛岋紝閲嶆柊璁剧疆閫夊尯
                $textElem.append($('<p><br></p>'));
                this.initSelection();
                return;
            }
        }

        this.selection.createRangeByElem($last, false, true);
        this.selection.restoreSelection();
    },

    // 缁戝畾浜嬩欢
    _bindEvent: function _bindEvent() {
        // -------- 缁戝畾 onchange 浜嬩欢 --------
        var onChangeTimeoutId = 0;
        var beforeChangeHtml = this.txt.html();
        var config$$1 = this.config;

        // onchange 瑙﹀彂寤惰繜鏃堕棿
        var onchangeTimeout = config$$1.onchangeTimeout;
        onchangeTimeout = parseInt(onchangeTimeout, 10);
        if (!onchangeTimeout || onchangeTimeout <= 0) {
            onchangeTimeout = 200;
        }

        var onchange = config$$1.onchange;
        if (onchange && typeof onchange === 'function') {
            // 瑙﹀彂 change 鐨勬湁涓変釜鍦烘櫙锛?            // 1. $textContainerElem.on('click keyup')
            // 2. $toolbarElem.on('click')
            // 3. editor.cmd.do()
            this.change = function () {
                // 鍒ゆ柇鏄惁鏈夊彉鍖?                var currentHtml = this.txt.html();

                if (currentHtml.length === beforeChangeHtml.length) {
                    // 闇€瑕佹瘮杈冩瘡涓€涓瓧绗?                    if (currentHtml === beforeChangeHtml) {
                        return;
                    }
                }

                // 鎵ц锛屼娇鐢ㄨ妭娴?                if (onChangeTimeoutId) {
                    clearTimeout(onChangeTimeoutId);
                }
                onChangeTimeoutId = setTimeout(function () {
                    // 瑙﹀彂閰嶇疆鐨?onchange 鍑芥暟
                    onchange(currentHtml);
                    beforeChangeHtml = currentHtml;
                }, onchangeTimeout);
            };
        }

        // -------- 缁戝畾 onblur 浜嬩欢 --------
        var onblur = config$$1.onblur;
        if (onblur && typeof onblur === 'function') {
            this.onblur = function () {
                var currentHtml = this.txt.html();
                onblur(currentHtml);
            };
        }

        // -------- 缁戝畾 onfocus 浜嬩欢 --------
        var onfocus = config$$1.onfocus;
        if (onfocus && typeof onfocus === 'function') {
            this.onfocus = function () {
                onfocus();
            };
        }
    },

    // 鍒涘缓缂栬緫鍣?    create: function create() {
        // 鍒濆鍖栭厤缃俊鎭?        this._initConfig();

        // 鍒濆鍖?DOM
        this._initDom();

        // 灏佽 command API
        this._initCommand();

        // 灏佽 selection range API
        this._initSelectionAPI();

        // 娣诲姞 text
        this._initText();

        // 鍒濆鍖栬彍鍗?        this._initMenus();

        // 娣诲姞 鍥剧墖涓婁紶
        this._initUploadImg();

        // 鍒濆鍖栭€夊尯锛屽皢鍏夋爣瀹氫綅鍒板唴瀹瑰熬閮?        this.initSelection(true);

        // 缁戝畾浜嬩欢
        this._bindEvent();
    },

    // 瑙ｇ粦鎵€鏈変簨浠讹紙鏆傛椂涓嶅澶栧紑鏀撅級
    _offAllEvent: function _offAllEvent() {
        $.offAll();
    }
};

// 妫€楠屾槸鍚︽祻瑙堝櫒鐜
try {
    document;
} catch (ex) {
    throw new Error('璇峰湪娴忚鍣ㄧ幆澧冧笅杩愯');
}

// polyfill
polyfill();

// 杩欓噷鐨?`inlinecss` 灏嗚鏇挎崲鎴?css 浠ｇ爜鐨勫唴瀹癸紝璇︽儏鍙幓 ./gulpfile.js 涓悳绱?`inlinecss` 鍏抽敭瀛?var inlinecss = '.w-e-toolbar,.w-e-text-container,.w-e-menu-panel {  padding: 0;  margin: 0;  box-sizing: border-box;}.w-e-toolbar *,.w-e-text-container *,.w-e-menu-panel * {  padding: 0;  margin: 0;  box-sizing: border-box;}.w-e-clear-fix:after {  content: "";  display: table;  clear: both;}.w-e-toolbar .w-e-droplist {  position: absolute;  left: 0;  top: 0;  background-color: #fff;  border: 1px solid #f1f1f1;  border-right-color: #ccc;  border-bottom-color: #ccc;}.w-e-toolbar .w-e-droplist .w-e-dp-title {  text-align: center;  color: #999;  line-height: 2;  border-bottom: 1px solid #f1f1f1;  font-size: 13px;}.w-e-toolbar .w-e-droplist ul.w-e-list {  list-style: none;  line-height: 1;}.w-e-toolbar .w-e-droplist ul.w-e-list li.w-e-item {  color: #333;  padding: 5px 0;}.w-e-toolbar .w-e-droplist ul.w-e-list li.w-e-item:hover {  background-color: #f1f1f1;}.w-e-toolbar .w-e-droplist ul.w-e-block {  list-style: none;  text-align: left;  padding: 5px;}.w-e-toolbar .w-e-droplist ul.w-e-block li.w-e-item {  display: inline-block;  *display: inline;  *zoom: 1;  padding: 3px 5px;}.w-e-toolbar .w-e-droplist ul.w-e-block li.w-e-item:hover {  background-color: #f1f1f1;}@font-face {  font-family: \'w-e-icon\';  src: url(data:application/x-font-woff;charset=utf-8;base64,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) format(\'truetype\');  font-weight: normal;  font-style: normal;}[class^="w-e-icon-"],[class*=" w-e-icon-"] {  /* use !important to prevent issues with browser extensions that change fonts */  font-family: \'w-e-icon\' !important;  speak: none;  font-style: normal;  font-weight: normal;  font-variant: normal;  text-transform: none;  line-height: 1;  /* Better Font Rendering =========== */  -webkit-font-smoothing: antialiased;  -moz-osx-font-smoothing: grayscale;}.w-e-icon-close:before {  content: "\\f00d";}.w-e-icon-upload2:before {  content: "\\e9c6";}.w-e-icon-trash-o:before {  content: "\\f014";}.w-e-icon-header:before {  content: "\\f1dc";}.w-e-icon-pencil2:before {  content: "\\e906";}.w-e-icon-paint-brush:before {  content: "\\f1fc";}.w-e-icon-image:before {  content: "\\e90d";}.w-e-icon-play:before {  content: "\\e912";}.w-e-icon-location:before {  content: "\\e947";}.w-e-icon-undo:before {  content: "\\e965";}.w-e-icon-redo:before {  content: "\\e966";}.w-e-icon-quotes-left:before {  content: "\\e977";}.w-e-icon-list-numbered:before {  content: "\\e9b9";}.w-e-icon-list2:before {  content: "\\e9bb";}.w-e-icon-link:before {  content: "\\e9cb";}.w-e-icon-happy:before {  content: "\\e9df";}.w-e-icon-bold:before {  content: "\\ea62";}.w-e-icon-underline:before {  content: "\\ea63";}.w-e-icon-italic:before {  content: "\\ea64";}.w-e-icon-strikethrough:before {  content: "\\ea65";}.w-e-icon-table2:before {  content: "\\ea71";}.w-e-icon-paragraph-left:before {  content: "\\ea77";}.w-e-icon-paragraph-center:before {  content: "\\ea78";}.w-e-icon-paragraph-right:before {  content: "\\ea79";}.w-e-icon-terminal:before {  content: "\\f120";}.w-e-icon-page-break:before {  content: "\\ea68";}.w-e-icon-cancel-circle:before {  content: "\\ea0d";}.w-e-icon-font:before {  content: "\\ea5c";}.w-e-icon-text-heigh:before {  content: "\\ea5f";}.w-e-toolbar {  display: -webkit-box;  display: -ms-flexbox;  display: flex;  padding: 0 5px;  /* flex-wrap: wrap; */  /* 鍗曚釜鑿滃崟 */}.w-e-toolbar .w-e-menu {  position: relative;  text-align: center;  padding: 5px 10px;  cursor: pointer;}.w-e-toolbar .w-e-menu i {  color: #999;}.w-e-toolbar .w-e-menu:hover i {  color: #333;}.w-e-toolbar .w-e-active i {  color: #1e88e5;}.w-e-toolbar .w-e-active:hover i {  color: #1e88e5;}.w-e-text-container .w-e-panel-container {  position: absolute;  top: 0;  left: 50%;  border: 1px solid #ccc;  border-top: 0;  box-shadow: 1px 1px 2px #ccc;  color: #333;  background-color: #fff;  /* 涓?emotion panel 瀹氬埗鐨勬牱寮?*/  /* 涓婁紶鍥剧墖鐨?panel 瀹氬埗鏍峰紡 */}.w-e-text-container .w-e-panel-container .w-e-panel-close {  position: absolute;  right: 0;  top: 0;  padding: 5px;  margin: 2px 5px 0 0;  cursor: pointer;  color: #999;}.w-e-text-container .w-e-panel-container .w-e-panel-close:hover {  color: #333;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-title {  list-style: none;  display: -webkit-box;  display: -ms-flexbox;  display: flex;  font-size: 14px;  margin: 2px 10px 0 10px;  border-bottom: 1px solid #f1f1f1;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-title .w-e-item {  padding: 3px 5px;  color: #999;  cursor: pointer;  margin: 0 3px;  position: relative;  top: 1px;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-title .w-e-active {  color: #333;  border-bottom: 1px solid #333;  cursor: default;  font-weight: 700;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content {  padding: 10px 15px 10px 15px;  font-size: 16px;  /* 杈撳叆妗嗙殑鏍峰紡 */  /* 鎸夐挳鐨勬牱寮?*/}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content input:focus,.w-e-text-container .w-e-panel-container .w-e-panel-tab-content textarea:focus,.w-e-text-container .w-e-panel-container .w-e-panel-tab-content button:focus {  outline: none;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content textarea {  width: 100%;  border: 1px solid #ccc;  padding: 5px;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content textarea:focus {  border-color: #1e88e5;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content input[type=text] {  border: none;  border-bottom: 1px solid #ccc;  font-size: 14px;  height: 20px;  color: #333;  text-align: left;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content input[type=text].small {  width: 30px;  text-align: center;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content input[type=text].block {  display: block;  width: 100%;  margin: 10px 0;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content input[type=text]:focus {  border-bottom: 2px solid #1e88e5;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button {  font-size: 14px;  color: #1e88e5;  border: none;  padding: 5px 10px;  background-color: #fff;  cursor: pointer;  border-radius: 3px;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button.left {  float: left;  margin-right: 10px;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button.right {  float: right;  margin-left: 10px;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button.gray {  color: #999;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button.red {  color: #c24f4a;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container button:hover {  background-color: #f1f1f1;}.w-e-text-container .w-e-panel-container .w-e-panel-tab-content .w-e-button-container:after {  content: "";  display: table;  clear: both;}.w-e-text-container .w-e-panel-container .w-e-emoticon-container .w-e-item {  cursor: pointer;  font-size: 18px;  padding: 0 3px;  display: inline-block;  *display: inline;  *zoom: 1;}.w-e-text-container .w-e-panel-container .w-e-up-img-container {  text-align: center;}.w-e-text-container .w-e-panel-container .w-e-up-img-container .w-e-up-btn {  display: inline-block;  *display: inline;  *zoom: 1;  color: #999;  cursor: pointer;  font-size: 60px;  line-height: 1;}.w-e-text-container .w-e-panel-container .w-e-up-img-container .w-e-up-btn:hover {  color: #333;}.w-e-text-container {  position: relative;}.w-e-text-container .w-e-progress {  position: absolute;  background-color: #1e88e5;  bottom: 0;  left: 0;  height: 1px;}.w-e-text {  padding: 0 10px;  overflow-y: scroll;}.w-e-text p,.w-e-text h1,.w-e-text h2,.w-e-text h3,.w-e-text h4,.w-e-text h5,.w-e-text table,.w-e-text pre {  margin: 10px 0;  line-height: 1.5;}.w-e-text ul,.w-e-text ol {  margin: 10px 0 10px 20px;}.w-e-text blockquote {  display: block;  border-left: 8px solid #d0e5f2;  padding: 5px 10px;  margin: 10px 0;  line-height: 1.4;  font-size: 100%;  background-color: #f1f1f1;}.w-e-text code {  display: inline-block;  *display: inline;  *zoom: 1;  background-color: #f1f1f1;  border-radius: 3px;  padding: 3px 5px;  margin: 0 3px;}.w-e-text pre code {  display: block;}.w-e-text table {  border-top: 1px solid #ccc;  border-left: 1px solid #ccc;}.w-e-text table td,.w-e-text table th {  border-bottom: 1px solid #ccc;  border-right: 1px solid #ccc;  padding: 3px 5px;}.w-e-text table th {  border-bottom: 2px solid #ccc;  text-align: center;}.w-e-text:focus {  outline: none;}.w-e-text img {  cursor: pointer;}.w-e-text img:hover {  box-shadow: 0 0 5px #333;}';

// 灏?css 浠ｇ爜娣诲姞鍒?<style> 涓?var style = document.createElement('style');
style.type = 'text/css';
style.innerHTML = inlinecss;
document.getElementsByTagName('HEAD').item(0).appendChild(style);

// 杩斿洖
var index = window.wangEditor || Editor;

return index;

})));
