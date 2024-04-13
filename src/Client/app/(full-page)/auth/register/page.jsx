'use client';
import { classNames } from 'primereact/utils';
import React, { useContext } from 'react';
import { LayoutContext } from '../../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { useRouter } from 'next/navigation';
import { Dropdown } from 'primereact/dropdown';
import { Calendar } from 'primereact/calendar';

const RegisterPage = () => {
    const { layoutConfig } = useContext(LayoutContext);

    const router = useRouter();
    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });

    const genderLookups = [
        { name: 'Male', code: 'MALE' },
        { name: 'Female', code: 'FEMALE' }
    ];

    const bloodGroupLookups = [
        { name: 'O+', code: 'O_POSITIVE' },
        { name: 'O-', code: 'O_NEGATIVE' }
    ];

    return (
        <div className={containerClassName}>
            <div className="flex flex-column align-items-center justify-content-center">
                <img src={`/layout/images/logo-${layoutConfig.colorScheme === 'light' ? 'dark' : 'white'}.svg`}
                     alt="Sakai logo" className="mb-5 w-6rem flex-shrink-0" />
                <div
                    style={{
                        borderRadius: '56px',
                        padding: '0.3rem',
                        background: 'linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}
                >
                    <div className="w-full surface-card py-8 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
                        <div className="text-center mb-7">
                            <div className="text-900 text-3xl font-medium mb-3">Create new account</div>
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
                                        <InputText id="firstName" type="text" className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="lastName" className="block font-medium mb-2">
                                            Last Name
                                        </label>
                                        <InputText id="lastName" type="text" className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="email" className="block font-medium mb-2">
                                            Email
                                        </label>
                                        <InputText id="email" type="text" className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="phoneNo" className="block font-medium mb-2">
                                            Phone Number
                                        </label>
                                        <InputText id="phoneNo" type="text" className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                            </div>
                            <div className="grid">
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="username" className="block font-medium mb-2">
                                            Username
                                        </label>
                                        <InputText id="username" type="text" className="w-full md:w-30rem mb-3" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="gender" className="block font-medium mb-2">
                                            Gender
                                        </label>
                                        <Dropdown id="gender"
                                                  className="w-full md:w-30rem mb-3"
                                                  options={genderLookups}
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
                                        <Dropdown id="bloodGroup" options={bloodGroupLookups}
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
                                        <Password id="password" toggleMask className="w-full mb-3"
                                                  inputClassName="w-full md:w-30rem" />
                                    </div>
                                </div>
                                <div className="col">
                                    <div className="field">
                                        <label htmlFor="confirmPassword" className="block font-medium mb-2">
                                            Confirm Password
                                        </label>
                                        <Password id="confirmPassword" toggleMask className="w-full mb-3"
                                                  inputClassName="w-full md:w-30rem" />
                                    </div>
                                </div>
                            </div>

                            <Button label="Register" className="p-3 text-xl"
                                    onClick={() => router.push('/')}></Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
