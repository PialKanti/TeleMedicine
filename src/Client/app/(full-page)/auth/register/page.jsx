'use client';
import { classNames } from 'primereact/utils';
import React, { useContext, useEffect, useState } from 'react';
import { LayoutContext } from '../../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { useRouter } from 'next/navigation';
import { Dropdown } from 'primereact/dropdown';
import { Calendar } from 'primereact/calendar';
import { getBloodGroups, getGenders } from '../../../services/lookup';
import { InputTextarea } from 'primereact/inputtextarea';
import { registerAsPatient } from '../../../services/auth';

const RegisterPage = () => {
    const { layoutConfig } = useContext(LayoutContext);

    const router = useRouter();
    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });

    const [bloodGroups, setBloodGroups] = useState([]);
    const [genders, setGenders] = useState([]);

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNo, setPhoneNo] = useState('');
    const [username, setUsername] = useState('');
    const [gender, setGender] = useState('');
    const [birthDate, setBirthDate] = useState(null);
    const [bloodGroup, setBloodGroup] = useState('');
    const [password, setPassword] = useState('');
    const [address, setAddress] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            const bloodGroupResult = await getBloodGroups();
            const genderResult = await getGenders();

            setBloodGroups(bloodGroupResult.data);
            setGenders(genderResult.data);
        };

        fetchData();
    }, []);

    const submitForm = async () => {
        const formData = getFormData();
        console.log('Form data: ', formData);
        const result = await registerAsPatient(formData);
        console.log('Result : ', result);
    };

    const getFormData = () => {
        return {
            firstName: firstName,
            lastName: lastName,
            username: username,
            email: email,
            password: password,
            phoneNo: phoneNo,
            dateOfBirth: birthDate,
            bloodGroup: bloodGroup,
            gender: gender,
            address: address
        };
    };

    return (
        <div className={containerClassName}>
            <div className="flex flex-column align-items-center justify-content-center mt-5">
                <div
                    style={{
                        borderRadius: '56px',
                        padding: '0.3rem',
                        background: 'linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}
                >
                    <div className="w-full surface-card py-5 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
                        <div className="text-center mb-6">
                            <div className="text-900 text-3xl font-medium mb-2">Create new account</div>
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
                                        <label htmlFor="dateOfBirth" className="block font-medium mb-2">
                                            Birth Date
                                        </label>
                                        <Calendar id="dateOfBirth"
                                                  value={birthDate}
                                                  onChange={(e) => setBirthDate(e.target.value)}
                                                  showIcon
                                                  showButtonBar
                                                  className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="bloodGroup" className="block font-medium mb-2">
                                            Blood Group
                                        </label>
                                        <Dropdown id="bloodGroup"
                                                  value={bloodGroup}
                                                  onChange={(e) => setBloodGroup(e.target.value)}
                                                  options={bloodGroups}
                                                  optionLabel="name"
                                                  optionValue="code"
                                                  placeholder="Select"
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
                            <div>
                                <label htmlFor="address" className="block font-medium mb-2">
                                    Address
                                </label>
                                <InputTextarea id="address"
                                               rows={2}
                                               value={address}
                                               onChange={(e) => setAddress(e.target.value)}
                                               className="w-full mb-3" />
                            </div>

                            <Button label="Register"
                                    className="p-3"
                                    onClick={submitForm} />
                            <div className="mt-5 text-center">
                                <span className="text-gray-500 font-medium">Are you a doctor?</span>
                                <a className="font-medium no-underline ml-1 text-right cursor-pointer"
                                   style={{ color: 'var(--primary-color)' }}
                                   onClick={() => router.push('/auth/register/doctor')}>
                                    Register as a doctor
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
