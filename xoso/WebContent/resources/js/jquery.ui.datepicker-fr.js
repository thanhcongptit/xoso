/* French initialisation for the jQuery UI date picker plugin. */

jQuery(function($){
	$.datepicker.regional['fr'] = {
		closeText: 'Ðóng',
		prevText: 'Truoc',
		nextText: 'Sau',
		//currentText: 'Aujourd\'hui',
		monthNames: ['Jan', 'Feb', 'Mat', 'Apr', 'May', 'Jun',
			'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
		
		dayNames: ['dimanche', 'lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi'],
		dayNamesShort: ['dim.', 'lun.', 'mar.', 'mer.', 'jeu.', 'ven.', 'sam.'],
		dayNamesMin: ['D','L','M','M','J','V','S'],
		weekHeader: 'Sem.',
		dateFormat: 'dd/mm/yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	$.datepicker.setDefaults($.datepicker.regional['fr']);
});
