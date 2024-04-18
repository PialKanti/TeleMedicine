'use client';

import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import React, { useEffect, useRef, useState } from 'react';
import { approveDoctor, getPendingDoctors } from '../../../services/doctor';
import { getTitles } from '../../../services/lookup';
import { Button } from 'primereact/button';
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';
import { Toast } from 'primereact/toast';
import { HttpStatusCode } from 'axios';

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

    const toast = useRef(null);

    const approve = async (doctor) => {
        const result = await approveDoctor(doctor.id);
        if (result.status === HttpStatusCode.Ok) {
            toast.current.show({
                severity: 'success',
                summary: 'Approved',
                detail: `${doctor.firstName} ${doctor.lastName} has been approved`,
                life: 2000
            });

            setDoctors(doctors.filter(item => item.id !== doctor.id));
        }
    };

    const confirm = (doctor) => {
        confirmDialog({
            message: 'Are you sure you want to approve?',
            header: 'Confirmation',
            icon: 'pi pi-info-circle',
            accept: () => approve(doctor)
        });
    };

    return (
        <>
            <Toast ref={toast} />
            <ConfirmDialog />
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
                            <Column header="Action" body={(rowData) => (
                                <Button label="Approve" icon="pi pi-check" size="small" severity="success" rounded
                                        outlined
                                        onClick={() => confirm(rowData)} />
                            )}></Column>
                        </DataTable>
                    </div>
                </div>
            </div>
        </>
    );
};

export default PendingDoctorsPage;
