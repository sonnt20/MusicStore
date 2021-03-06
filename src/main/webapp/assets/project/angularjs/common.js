var app = angular.module('FrameworkBase', ["ngSanitize", "autocomplete"]);

//Bat ky tu
var digitsOnly = /[1234567890]/g;
var digitsAndSemicolon = /[1234567890;]/g;
var digitsAndAsterisk = /[1234567890\\*]/g;
var digitsAndSlash = /[1234567890/]/g;

function restrictCharacters(myfield, e, restrictionType) {
    if (!e) var e = window.event
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    var character = String.fromCharCode(code);

    // if they pressed esc... remove focus from field...
    if (code == 27) {
        this.blur();
        return false;
    }

    // ignore if they are press other keys
    // strange because code: 39 is the down key AND ' key...
    // and DEL also equals .
    if (!e.ctrlKey && code != 9 && code != 8 && code != 36 && code != 37 && code != 38 && (code != 39 || (code == 39 && character == "'")) && code != 40) {
        if (character.match(restrictionType)) {
            return true;
        } else {
            return false;
        }

    }
}

function updatePage($scope, response) {
    debugger;
    if (response != null && response != 'undefined' && response.status == 200) {
        $scope.page = response.data;
        $scope.page.pageList = getPageList($scope.page);
        $scope.page.pageCount = getPageCount($scope.page);
    }
}

app.directive("selectNgFiles", function() {
    return {
        require: "ngModel",
        link: function postLink(scope,elem,attrs,ngModel) {
            elem.on("change", function(e) {
                var files = elem[0].files;
                ngModel.$setViewValue(files);
            })
        }
    }
});

/*ap dung voi dinh dang 1,000,000 con neu muon 1.000.000 thi duoi'*/
app.directive('format', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;

            ctrl.$formatters.unshift(function (a) {
                return $filter(attrs.format)(ctrl.$modelValue)
            });

            ctrl.$parsers.unshift(function (viewValue) {
                var plainNumber = viewValue.replace(/[^\d|\-+|\.+]/g, '');
                plainNumber = plainNumber.replace(",", ".");
                elem.val($filter(attrs.format)(plainNumber));
                return plainNumber;
            });
        }
    };
}]);

/*KHU V???C PH??N TRANG JAVASCRIPT*/
var page = {items: "", rowCount: 0, numberPerPage: 20, pageNumber: 1, pageList: [], pageCount: 0};

/*load tong trang va danh sach trang*/
function getPageCount(pageResult) {
    var pageCount = Math.ceil(pageResult.rowCount / pageResult.numberPerPage);
    return pageCount;
}

/*TR??? gi??p t??nh to??n s??? trang hi???n th??? khi hi???n page*/
function getPageList(pagingResult) {
    var pages = [];
    var from = pagingResult.pageNumber - 3;
    var to = pagingResult.pageNumber + 5;
    if (from < 0) {
        to -= from;
        from = 1;
    }

    if (from < 1) {
        from = 1;
    }

    if (to > pagingResult.pageCount) {
        to = pagingResult.pageCount;
    }

    for (var i = from; i <= to; i++) {
        pages.push(i);
    }
    return pages;
}


//convert date dd/mm/yyyy sang date cua he thong.
function formatDate(strDate) {
    if (strDate == null || strDate.length != 10) return null;
    var dateArray = strDate.split("/");
    var date = dateArray[2] + "-" + dateArray[1] + "-" + dateArray[0];
    // alert(moment(moment(date).format("YYYY/MM/DD"),"YYYY/MM/DD",true).isValid());
    if (moment(date, "YYYY-MM-DD", true).isValid()) {
        return new Date(date);
    } else {
        return null;
    }

}

function formatDateDDMMYYYY(strDate) {
    if (strDate == null || strDate.length != 10) return null;
    var dateArray = strDate.split("-");
    var date = dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
    // alert(moment(moment(date).format("YYYY/MM/DD"),"YYYY/MM/DD",true).isValid());
    if (moment(date, "DD-MM-YYYY", true).isValid()) {
        return new Date(date);
    } else {
        return null;
    }

}

//convert mm/dd or mm-dd  sang dd/mm or dd-mm.
function formatStringDate(strDate) {
    if (strDate == null || strDate.length <= 0)
        return null;
    var dd = "";
    var mm = "";
    if (strDate.indexOf("/") > 0) {
        var dateArray = strDate.split("/");
        if (dateArray[1].startsWith("0")) {
            dd = dateArray[1].substring(1, 2);
        } else {
            dd = dateArray[1]
        }
        if (dateArray[0].startsWith("0")) {
            mm = dateArray[0].substring(1, 2);
        } else {
            mm = dateArray[0]
        }
        return dd + "/" + mm;

    } else if (strDate.indexOf("-") > 0) {
        var dateArray = strDate.split("-");
        if (dateArray[1].startsWith("0")) {
            dd = dateArray[1].substring(1, 2);
        } else {
            dd = dateArray[1]
        }
        if (dateArray[0].startsWith("0")) {
            mm = dateArray[0].substring(1, 2);
        } else {
            mm = dateArray[0]
        }
        return dd + "-" + mm;
    } else {
        return strDate;
    }
}

/*When click my-enter*/
app.directive('myEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if (event.which === 13) {
                scope.$apply(function () {
                    scope.$eval(attrs.myEnter);
                });

                event.preventDefault();
            }
        });
    };
});

/*<input type="text" datetime ng-model="defaultDate" dp-format="DD/MM/YYYY" min-date="minDate" max-date="maxDate"/>*/
/*$('#fromDateSign_').data("DateTimePicker").minDate(moment($('#toDateSign_').val(), "DD-MM-YYYY").toDate());*/
/*$('#toDateSign_').data("DateTimePicker").minDate(moment($('#fromDateSign_').val(), "DD-MM-YYYY").toDate());*/
app.directive('datetime', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        compile: function () {
            return {
                pre: function (scope, element, attrs, ngModelCtrl) {
                    var obj = {locale: 'vi-VN'};
                    var format = (!attrs.dpFormat) ? 'DD/MM/YYYY HH:mm:ss' : attrs.dpFormat;
                    obj.format = format;
                    obj.showClear = true;

                    var actualScopyThingy = element.scope();
                    if (isNotUndefined(attrs.minDate)) {
                        obj.minDate = actualScopyThingy.minDate;
                        if (isUndefined(obj.minDate)) {
                            obj.minDate = moment(attrs.minDate, format).toDate();
                        }
                    }
                    if (isNotUndefined(attrs.maxDate)) {
                        obj.maxDate = actualScopyThingy.maxDate;
                        if (isUndefined(obj.maxDate)) {
                            obj.maxDate = moment(attrs.maxDate, format).toDate();
                        }
                    }

                    // Initialize the date-picker
                    $(element).datetimepicker(obj).on('dp.change', function (e) {
                        if (e != null) {
                            scope.$apply(function () {
                                if (isNotUndefined($(element).val())) {
                                    ngModelCtrl.$setViewValue(moment($(element).val(), format).toDate());
                                } else {
                                    ngModelCtrl.$setViewValue("");
                                }
                            });
                        }
                    });
                }
            }
        }
    }
});

function getWeekNumber(d) {
    // Copy date so don't modify original
    d = new Date(Date.UTC(d.getFullYear(), d.getMonth(), d.getDate()));
    // Set to nearest Thursday: current date + 4 - current day number
    // Make Sunday's day number 7
    d.setUTCDate(d.getUTCDate() + 4 - (d.getUTCDay() || 7));
    // Get first day of year
    var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1));
    // Calculate full weeks to nearest Thursday
    var weekNo = Math.ceil((((d - yearStart) / 86400000) + 1) / 7);
    // Return array of year and week number
    return weekNo;
}

function isUndefined(value) {
    if (typeof value == "undefined" || value == null || value == "" || value.length == 0) {
        return true;
    }
    return false;
}

function isNotUndefined(value) {
    if (typeof value == "undefined" || value == null || value == "" || value.length == 0) {
        return false;
    }
    return true;
}

function stringToDate(_date, _format, _delimiter) {
    if (typeof _date == "undefined" || _date == "" || _date == null) return "";
    var timenow = new Date();
    var formatLowerCase = _format.toLowerCase();
    var formatItems = formatLowerCase.split(_delimiter);
    var dateItems = _date.split(_delimiter);
    var monthIndex = formatItems.indexOf("mm");
    var dayIndex = formatItems.indexOf("dd");
    var yearIndex = formatItems.indexOf("yyyy");
    var month = parseInt(dateItems[monthIndex]);
    month -= 1;
    var formatedDate = new Date(dateItems[yearIndex], month, dateItems[dayIndex], timenow.getHours(), timenow.getMinutes(), timenow.getSeconds(), '00');
    return formatedDate;
}

function stringToDateTime(_date) {
    var dateTimeParts = _date.split(' '),
        timeParts = dateTimeParts[1].split(':'),
        dateParts = dateTimeParts[0].split('-'),
        date;
    date = new Date(dateParts[2], parseInt(dateParts[1], 10) - 1, dateParts[0], timeParts[0], timeParts[1], timeParts[2]);
    date.toLocaleString('vi-VN', {timeZone: 'Asia/Ho_Chi_Minh'});
    return date;
}

//DD/MM/YYYY HH:mm
function stringToDateTimeDDMMYYYYHHMM(_date) {
    var dateTimeParts = _date.split(' '),
        timeParts = dateTimeParts[1].split(':'),
        dateParts = dateTimeParts[0].split('/'),
        date;
    date = new Date(dateParts[2], parseInt(dateParts[1], 10) - 1, dateParts[0], timeParts[0], timeParts[1], "00");
    date.toLocaleString('vi-VN', {timeZone: 'Asia/Ho_Chi_Minh'});
    return date;
}

function Date2String(date) {
    let dateStr = new Date(date);
    return moment(dateStr).format('DD/MM/YYYY');
};

function Date2String2(date) {
    let dateStr = new Date(date);
    return moment(dateStr).format('DD-MM-YYYY');
};

angular.module('FrameworkBase').filter('filter', function () {
    return function (value, stringdate) {
        var stringDate = stringDate;
        var convertedDate = new Date(stringDate);
        return convertedDate
    };
});

app.directive('customOnChange', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var onChangeHandler = scope.$eval(attrs.customOnChange);
            element.on('change', onChangeHandler);
            element.on('$destroy', function () {
                element.off();
            });

        }
    };
});

function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
};

function showDropDownOnTable() {
    $('.table-responsive').on('show.bs.dropdown', function () {
        $('.table-responsive').css("overflow", "inherit");
    });

    $('.table-responsive').on('hide.bs.dropdown', function () {
        $('.table-responsive').css("overflow", "auto");
    })
}

function getPageListNotCount(page, isHome, checkLast) {
    var pages = [];
    var from;
    var to;
    if (isHome == 1 && page < 10) {
        from = 1;
        to = 10

    } else if (isHome == 1 && page >= 10 && checkLast == 0) {
        from = page - 8;
        to = page + 1
    } else if (isHome == 1 && page >= 10 && checkLast == 1) {
        from = page - 9;
        to = page;
    } else if (isHome == 0 && checkLast == 0) {
        from = page - 3 > 0 ? page - 3 : 1;
        to = page + 1;
    } else if (isHome == 0 && checkLast == 1) {
        from = page - 4 > 0 ? page - 4 : 1;
        to = page;
    }
    for (var i = from; i <= to; i++) {
        pages.push(i);
    }
    return pages;
}

function show_dialog(_title, _message, _focus) {
    BootstrapDialog.show({
        title: _title,
        message: _message,
        buttons: [
            {
                label: '????ng',
                action: function (dialogItself) {
                    dialogItself.close();
                    if (_focus != undefined) {
                        $(_focus).focus();
                        if ($(_focus).is("select")) {
                            $(_focus).select2("open");
                        }
                    }
                }
            }]
    });

}

function formatCurrencyHtml(ctrl) {
    var val = ctrl.innerHTML;
    val = val.replace(/,/g, "");
    val += '';
    x = val.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';

    var rgx = /(\d+)(\d{3})/;

    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }

    ctrl.innerHTML = x1 + x2;
}

function daysDiffStringDDMMYYYY(date1, date2) {
    var date3 = moment(date1, 'DD/MM/YYYY');
    var date4 = moment(date2, 'DD/MM/YYYY');
    return date4.diff(date3, 'days');
}

$(document).ready(function () {
    $("#logoutButton").change(function () {
        $http.post(preUrl + "/logout", "", {headers: {'Content-Type': 'application/json'}})
            .then(function (response) {

            }, function (response) {
                toastr.error("L???i khi ????ng xu???t!!");
            });

    })
});
