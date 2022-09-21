<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <ul class="navbar-nav mx-auto pt-2 pt-lg-0 font-base">
              <li class="nav-item px-2"><a class="nav-link" aria-current="page" href="${cPath }/">HOME</a></li>
              <li class="nav-item px-2"><a class="nav-link" href="${cPath}/notice">NOTICE</a></li>
              <li class="nav-item px-2"><a class="nav-link" href="${cPath}/cooboard">COMMNUNITY </a></li>
              <li class="nav-item px-2"><a class="nav-link" href="${cPath}/blog/latest">BLOG </a></li>
              <li class="nav-item px-2"><a class="nav-link" href="${cPath}/expert">EXPERT </a></li>
            </ul>
            
<!-- Channel Plugin Scripts -->
<script>
  (function() {
    var w = window;
    if (w.ChannelIO) {
      return (window.console.error || window.console.log || function(){})('ChannelIO script included twice.');
    }
    var ch = function() {
      ch.c(arguments);
    };
    ch.q = [];
    ch.c = function(args) {
      ch.q.push(args);
    };
    w.ChannelIO = ch;
    function l() {
      if (w.ChannelIOInitialized) {
        return;
      }
      w.ChannelIOInitialized = true;
      var s = document.createElement('script');
      s.type = 'text/javascript';
      s.async = true;
      s.src = 'https://cdn.channel.io/plugin/ch-plugin-web.js';
      s.charset = 'UTF-8';
      var x = document.getElementsByTagName('script')[0];
      x.parentNode.insertBefore(s, x);
    }
    if (document.readyState === 'complete') {
      l();
    } else if (window.attachEvent) {
      window.attachEvent('onload', l);
    } else {
      window.addEventListener('DOMContentLoaded', l, false);
      window.addEventListener('load', l, false);
    }
  })();
  ChannelIO('boot', {
    "pluginKey": "d35b9f12-1f06-48bc-98ed-c420fe902997"
  });
</script>
<!-- End Channel Plugin -->