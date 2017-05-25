$(function () {
	var values = [],
		labels = [];
	$("#chart_data01 tr").each(function () {
		values.push(parseInt($("td", this).text(), 10));
		labels.push($("th", this).text());
	});
	$("#chart_data01").hide();
	Raphael("chart_box01", 500, 350).pieChart(250, 170, 100, values, labels, "#fff");
});

Raphael.fn.pieChart = function (cx, cy, r, values, labels, stroke) {
	var paper = this,
		rad = Math.PI / 180,
		chart = this.set();
	function sector(cx, cy, r, startAngle, endAngle, params) {
		var x1 = cx + r * Math.cos(-startAngle * rad),
			x2 = cx + r * Math.cos(-endAngle * rad),
			y1 = cy + r * Math.sin(-startAngle * rad),
			y2 = cy + r * Math.sin(-endAngle * rad);
		return paper.path(["M", cx, cy, "L", x1, y1, "A", r, r, 0, +(endAngle - startAngle > 180), 0, x2, y2, "z"]).attr(params);
	}
	var angle = 90,
		total = 0,
		start = 0,
		process = function (j) {
			var value = values[j];
			var angleplus = 360 * value / total;
			var popangle = angle + (angleplus / 2),
				color = Raphael.hsb(start, .75, 1),
				ms = 500,
				delta = 30,
				bcolor = Raphael.hsb(start, 1, 1),
				p = sector(cx, cy, r, angle, angle + angleplus, {fill: "90-" + bcolor + "-" + color, stroke: stroke, "stroke-width": 0}),
				txt = paper.text(cx + (r + delta + 45) * Math.cos(-popangle * rad), cy + (r + delta + 15) * Math.sin(-popangle * rad), labels[j]).attr({fill: bcolor, stroke: "none", "font-size": 14});

			angle += angleplus;
			chart.push(p);
			chart.push(txt);
			start += .1;
		};
	for (var i = 0, ii = values.length; i < ii; i++) {
		total += values[i];
	}
	for (i = 0; i < ii; i++) {
		process(i);
	}
	return chart;
};

