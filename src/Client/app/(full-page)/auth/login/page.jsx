/* eslint-disable @next/next/no-img-element */
'use client';
import { useRouter } from 'next/navigation';
import React, { useContext, useState } from 'react';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { LayoutContext } from '../../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import { login } from '../../../services/auth';
import { HttpStatusCode } from 'axios';
import useAuthStore from '../../../../stores/store';

const LoginPage = () => {
    const [loading, setLoading] = useState(false);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [checked, setChecked] = useState(false);
    const { layoutConfig } = useContext(LayoutContext);

    const router = useRouter();
    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });

    const { token, setToken } = useAuthStore();

    const handleLogin = async () => {
        setLoading(true);
        try {
            const result = await login(username, password);
            if (result.status === HttpStatusCode.Ok) {
                setToken(result.data.access_token);
                router.push('/');
            }
            console.log(result);
        } catch (error) {
            console.log('Error occurred: ', error);
        } finally {
            setLoading(false);
        }
    };

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
                            <div className="text-900 text-3xl font-medium mb-3">Sign In</div>
                            <div>
                                <span className="text-gray-500 font-medium">Not registered yet?</span>
                                <a className="font-medium no-underline ml-1 text-right cursor-pointer"
                                   style={{ color: 'var(--primary-color)' }}
                                   onClick={() => router.push('/auth/register')}>
                                    Register
                                </a>
                            </div>
                        </div>

                        <div>
                            <label htmlFor="username" className="block text-900 text-xl font-medium mb-2">
                                Username
                            </label>
                            <InputText id="username"
                                       type="text"
                                       value={username}
                                       onChange={(e) => setUsername(e.target.value)}
                                       placeholder="Enter username"
                                       className="w-full md:w-30rem mb-5"
                                       style={{ padding: '1rem' }} />

                            <label htmlFor="password1" className="block text-900 font-medium text-xl mb-2">
                                Password
                            </label>
                            <Password inputId="password1"
                                      value={password}
                                      onChange={(e) => setPassword(e.target.value)}
                                      placeholder="Enter password"
                                      toggleMask className="w-full mb-5"
                                      feedback={false}
                                      inputClassName="w-full p-3 md:w-30rem" />

                            <div className="flex align-items-center justify-content-between mb-5 gap-5">
                                <div className="flex align-items-center">
                                    <Checkbox inputId="rememberme1" checked={checked}
                                              onChange={(e) => setChecked(e.checked ?? false)}
                                              className="mr-2"></Checkbox>
                                    <label htmlFor="rememberme1">Remember me</label>
                                </div>
                                <a className="font-medium no-underline ml-2 text-right cursor-pointer"
                                   style={{ color: 'var(--primary-color)' }}>
                                    Forgot password?
                                </a>
                            </div>
                            <Button label="Sign In"
                                    className="w-full p-3 text-xl"
                                    loading={loading}
                                    onClick={handleLogin}></Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
