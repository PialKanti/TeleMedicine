'use client';

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import React, { useEffect, useState } from 'react';
import { getApprovedDoctors, getPendingDoctors } from '../../../services/doctor';
import { getTitles } from '../../../services/lookup';

const PendingDoctorsPage = () => {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            const titleResponse = await getTitles();

            const result = await getPendingDoctors();
            setDoctors(processData(result.data.data, titleResponse.data));
        };

        fetchData();
    }, []);

    const processData = (items, titles) => {
        console.log(titles);
        return items.map(item => ({
            ...item,
            name: getTitleNameByCode(titles, item.title) + ' ' + item.firstName + ' ' + item.lastName
        }));
    };

    const getTitleNameByCode = (titles, code) => {
        const title = titles.find(title => title.code === code);
        return title ? title.name : '';
    };

    return (
        <div className="grid">
            <div className="col-12">
                <div className="card">
                    <h5>Pending Doctors</h5>
                    <DataTable value={doctors}>
                        <Column field="name" header="Name"></Column>
                        <Column field="email" header="Email"></Column>
                        <Column field="phoneNo" header="Mobile Number"></Column>
                        <Column field="registrationNumber" header="Registration No (BMDC)"></Column>
                        <Column field="nidNumber" header="NID number"></Column>
                    </DataTable>
                </div>
            </div>
        </div>
    );
};

export default PendingDoctorsPage;
