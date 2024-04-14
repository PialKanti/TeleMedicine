'use client';
import { classNames } from 'primereact/utils';
import React, { useContext, useEffect, useState } from 'react';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { Dropdown } from 'primereact/dropdown';
import { getGenders, getSpecialities, getTitles } from '../../../../services/lookup';
import { LayoutContext } from '../../../../../layout/context/layoutcontext';
import { registerAsDoctor } from '../../../../services/auth';

const RegisterDoctorPage = () => {
    const { layoutConfig } = useContext(LayoutContext);

    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });

    const [titles, setTitles] = useState([]);
    const [specialities, setSpecialities] = useState([]);
    const [genders, setGenders] = useState([]);

    const [title, setTitle] = useState('');
    const [speciality, setSpeciality] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [username, setUsername] = useState('');
    const [gender, setGender] = useState('');
    const [registrationNo, setRegistrationNo] = useState('');
    const [nidNumber, setNidNumber] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const titleResult = await getTitles();
            const specialityResult = await getSpecialities();
            const genderResult = await getGenders();

            setTitles(titleResult.data);
            setSpecialities(specialityResult.data.data);
            setGenders(genderResult.data);
        };

        fetchData();
    }, []);

    const submitForm = async () => {
        const formData = getFormData();
        console.log('Form data: ', formData);
        const result = await registerAsDoctor(formData);
        console.log('Result : ', result);
    };

    const getFormData = () => {
        return {
            title: title,
            specialityId: speciality,
            firstName: firstName,
            lastName: lastName,
            username: username,
            email: email,
            password: password,
            phoneNo: phoneNo,
            gender: gender,
            registrationNumber: registrationNo,
            nidNumber: nidNumber
        };
    };

    return (
        <div className={containerClassName}>
            <div className="flex flex-column align-items-center justify-content-center">
                <div
                    style={{
                        borderRadius: '56px',
                        padding: '0.3rem',
                        background: 'linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}
                >
                    <div className="w-full surface-card py-5 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
                        <div className="text-center mb-6">
                            <div className="text-900 text-3xl font-medium mb-2">Doctor Registration</div>
                            <div>
                                <span className="text-gray-500 font-medium">Have an account?</span>
                                <a className="font-medium no-underline ml-1 text-right cursor-pointer"
                                   style={{ color: 'var(--primary-color)' }}>
                                    Login
                                </a>
                            </div>
                        </div>

                        <div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="title" className="block font-medium mb-2">
                                            Title
                                        </label>
                                        <Dropdown id="title"
                                                  value={title}
                                                  options={titles}
                                                  optionLabel="name"
                                                  optionValue="code"
                                                  placeholder="Selct"
                                                  onChange={(e) => setTitle(e.target.value)}
                                                  className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="speciality" className="block font-medium mb-2">
                                            Speciality
                                        </label>
                                        <Dropdown id="speciality"
                                                  value={speciality}
                                                  options={specialities}
                                                  optionLabel="name"
                                                  optionValue="id"
                                                  placeholder="Selct"
                                                  onChange={(e) => setSpeciality(e.target.value)}
                                                  className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="firstName" className="block font-medium mb-2">
                                            First Name
                                        </label>
                                        <InputText id="firstName"
                                                   type="text"
                                                   value={firstName}
                                                   onChange={(e) => setFirstName(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="lastName" className="block font-medium mb-2">
                                            Last Name
                                        </label>
                                        <InputText id="lastName"
                                                   type="text"
                                                   value={lastName}
                                                   onChange={(e) => setLastName(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="email" className="block font-medium mb-2">
                                            Email
                                        </label>
                                        <InputText id="email"
                                                   type="text"
                                                   value={email}
                                                   onChange={(e) => setEmail(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="phoneNo" className="block font-medium mb-2">
                                            Phone Number
                                        </label>
                                        <InputText id="phoneNo"
                                                   type="text"
                                                   value={phoneNo}
                                                   onChange={(e) => setPhoneNo(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="username" className="block font-medium mb-2">
                                            Username
                                        </label>
                                        <InputText id="username"
                                                   type="text"
                                                   value={username}
                                                   onChange={(e) => setUsername(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="gender" className="block font-medium mb-2">
                                            Gender
                                        </label>
                                        <Dropdown id="gender"
                                                  className="w-full md:w-30rem mb-3"
                                                  value={gender}
                                                  onChange={(e) => setGender(e.target.value)}
                                                  options={genders}
                                                  optionLabel="name"
                                                  optionValue="code"
                                                  placeholder="Select" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="registrationNo" className="block font-medium mb-2">
                                            Registration Number (BMDC)
                                        </label>
                                        <InputText id="registrationNo"
                                                   type="text"
                                                   value={registrationNo}
                                                   onChange={(e) => setRegistrationNo(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="nidNumber" className="block font-medium mb-2">
                                            National ID
                                        </label>
                                        <InputText id="nidNumber"
                                                   type="text"
                                                   value={nidNumber}
                                                   onChange={(e) => setNidNumber(e.target.value)}
                                                   className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="password" className="block font-medium mb-2">
                                            Password
                                        </label>
                                        <Password id="password"
                                                  value={password}
                                                  onChange={(e) => setPassword(e.target.value)}
                                                  toggleMask
                                                  feedback={false}
                                                  className="w-full mb-3"
                                                  inputClassName="w-full md:w-30rem" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="confirmPassword" className="block font-medium mb-2">
                                            Confirm Password
                                        </label>
                                        <Password id="confirmPassword"
                                                  toggleMask
                                                  feedback={false}
                                                  className="w-full mb-3"
                                                  inputClassName="w-full md:w-30rem" />
                                    </div>
                                </div>
                            </div>

                            <Button label="Register"
                                    className="p-3"
                                    onClick={submitForm} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterDoctorPage;
