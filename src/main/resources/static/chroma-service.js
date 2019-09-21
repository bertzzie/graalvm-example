(function () {

    return {
        darkenSaturateHex: function (source, color, saturate) {
            load(source);
            return chroma(color).darken().saturate(saturate).hex();
        }
    };
})();

