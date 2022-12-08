'use strict';

const email = document.querySelector('input[name=email]');
const password = document.querySelector('input[name=password]');
const password_confirm = document.querySelector('input[name=password_confirm]');
const name = document.querySelector('input[name=name]');
const gender = document.querySelector('input[name=gender]:checked');
const userRole = document.querySelector('select[name=userRole]');
const status = document.querySelector('select[name=status]');

async function fnRegist() {

    if (email.value === '') {
        alert('E-mail을 입력해주세요.');
        email.focus();
        return false;
    }

    if (password.value === '') {
        alert('비밀번호를 입력해주세요');
        password.focus();
        return false;
    }

    if (password_confirm.value === '') {
        alert('비밀번호 확인을 입력해주세요.');
        password_confirm.focus();
        return false;
    }
    if (password.value !== password_confirm.value) {
        alert('비밀번호가 일치하지 않습니다.');
        password_confirm.focus();
        return false;
    }

    if (name.value === '') {
        alert('이름을 입력해주세요');
        name.focus();
        return false;
    }

    if (gender == null) {
        alert('성별을 선택해주세요.');
        return false;
    }

    if (userRole.value === '') {
        alert('권한을 선택해주세요.');
        return false;
    }

    if (status.value === '') {
        alert('상태를 선택해주세요.');
        return false;
    }

    const updateUrl = './';
    const response = await fetch(updateUrl, {
        method : 'post',
        headers: {
            'Content-Type': 'application/json',
        },
        body : JSON.stringify({
            email : email.value,
            password : password.value,
            name : name.value,
            userRole : userRole.value,
            status : status.value,
            gender : gender.value
        }),
    });

    const data = await response.json();

    if (data.res_cd !== '0000') {
        alert(data.res_msg);
        return false;
    }

    alert('등록되었습니다.');

    const res_data = JSON.parse(data.res_data);
    location.href = `./${res_data.id}`;
}

async function fnUpdate() {

    if (email.value === '') {
        alert('E-mail을 입력해주세요.');
        email.focus();
        return false;
    }

    if (password.value !== '') {
        // password 정책
        if (password_confirm.value === '') {
            alert('비밀번호 확인을 입력해주세요.');
            password_confirm.focus();
            return false;
        }
        if (password.value !== password_confirm.value) {
            alert('비밀번호가 일치하지 않습니다.');
            password_confirm.focus();
            return false;
        }
    }

    if (name.value === '') {
        alert('이름을 입력해주세요');
        name.focus();
        return false;
    }

    if (gender == null) {
        alert('성별을 선택해주세요.');
        return false;
    }

    if (userRole.value === '') {
        alert('권한을 선택해주세요.');
        return false;
    }

    if (status.value === '') {
        alert('상태를 선택해주세요.');
        return false;
    }

    const updateUrl = `./${Member.id}`;
    const response = await fetch(updateUrl, {
        method : 'put',
        headers: {
            'Content-Type': 'application/json',
        },
        body : JSON.stringify({
            id : Member.id,
            email : email.value,
            password : password.value,
            name : name.value,
            userRole : userRole.value,
            status : status.value,
            gender : gender.value
        }),
    });

    const data = await response.json();

    if (data.res_cd !== '0000') {
        alert(data.res_msg);
        return false;
    }

    alert('수정되었습니다.');
}